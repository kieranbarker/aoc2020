package codes.barker.day04;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        long answer = solve();
        System.out.println(answer);

        long answer2 = solve2();
        System.out.println(answer2);
    }

    private static long solve() {
        String input = readInput("day04.txt");
        String[] passports = input.split("\\n\\n");
        return Arrays.stream(passports).filter(Program::isValidPassport).count();
    }

    private static long solve2() {
        String input = readInput("day04.txt");
        String[] passports = input.split("\\n\\n");
        return Arrays.stream(passports).filter(Program::isValidPassport2).count();
    }

    private static boolean isValidPassport(String passport) {
        Set<String> fields = Arrays.stream(passport.split("\\s+"))
                .map(pair -> pair.split(":")[0])
                .collect(Collectors.toSet());

        Set<String> requiredFields = new HashSet<>(List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
        return fields.containsAll(requiredFields);
    }

    private static boolean isValidPassport2(String passport) {
        Map<String, String> fields = Arrays.stream(passport.split("\\s+"))
                .map(pair -> pair.split(":"))
                .collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));

        Set<String> requiredFields = new HashSet<>(List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
        if (!fields.keySet().containsAll(requiredFields)) return false;

        String birthYear = fields.get("byr");
        if (!isValidBirthYear(birthYear)) return false;

        String issueYear = fields.get("iyr");
        if (!isValidIssueYear(issueYear)) return false;

        String expirationYear = fields.get("eyr");
        if (!isValidExpirationYear(expirationYear)) return false;

        String height = fields.get("hgt");
        if (!isValidHeight(height)) return false;

        String hcl = fields.get("hcl");
        if (!isValidHairColor(hcl)) return false;

        String eyeColor = fields.get("ecl");
        if (!isValidEyeColor(eyeColor)) return false;

        String passportID = fields.get("pid");
        return isValidPassportID(passportID);
    }

    private static boolean isValidBirthYear(String birthYear) {
        int parsed = Integer.parseInt(birthYear);
        return parsed >= 1920 && parsed <= 2002;
    }

    private static boolean isValidIssueYear(String issueYear) {
        int parsed = Integer.parseInt(issueYear);
        return parsed >= 2010 && parsed <= 2020;
    }

    private static boolean isValidExpirationYear(String expirationYear) {
        int parsed = Integer.parseInt(expirationYear);
        return parsed >= 2020 && parsed <= 2030;
    }

    private static boolean isValidHeight(String height) {
        if (height.endsWith("cm")) {
            int parsed = Integer.parseInt(height.substring(0, height.length() - 2));
            return parsed >= 150 && parsed <= 193;
        }

        if (height.endsWith("in")) {
            int parsed = Integer.parseInt(height.substring(0, height.length() - 2));
            return parsed >= 59 && parsed <= 76;
        }

        return false;
    }

    private static boolean isValidHairColor(String hairColor) {
        String regex = "^#([0-9a-f]{6})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(hairColor);
        return matcher.matches();
    }

    private static boolean isValidEyeColor(String eyeColor) {
        Set<String> eyeColors = new HashSet<>(List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
        return eyeColors.contains(eyeColor);
    }

    private static boolean isValidPassportID(String passportID) {
        String regex = "^\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(passportID);
        return matcher.matches();
    }

    private static String readInput(String name) {
        try (
                InputStream inputStream = codes.barker.day03.Program.class.getClassLoader().getResourceAsStream(name);
                var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                var bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}