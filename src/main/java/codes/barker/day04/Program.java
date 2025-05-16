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
    private static final String input = readInput("day04.txt");
    private static final String[] passports = input.split("\\n\\n");
    private static final Set<String> requiredFields = new HashSet<>(List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));

    public static void main(String[] args) {
        long answer = solve();
        System.out.println(answer);

        long answer2 = solve2();
        System.out.println(answer2);
    }

    public static long solve() {
        return Arrays.stream(passports).filter(Program::isValidPassport).count();
    }

    public static long solve2() {
        return Arrays.stream(passports).filter(Program::isValidPassport2).count();
    }

    public static boolean isValidPassport(String passport) {
        Set<String> fields = Arrays.stream(passport.split("\\s+"))
                .map(pair -> pair.split(":")[0])
                .collect(Collectors.toSet());
        return fields.containsAll(requiredFields);
    }

    public static boolean isValidPassport2(String passport) {
        Map<String, String> fields = Arrays.stream(passport.split("\\s+"))
                .map(pair -> pair.split(":"))
                .collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));
        if (!fields.keySet().containsAll(requiredFields)) return false;

        int byr = Integer.parseInt(fields.get("byr"));
        if (byr < 1920 || byr > 2002) return false;

        int iyr = Integer.parseInt(fields.get("iyr"));
        if (iyr < 2010 || iyr > 2020) return false;

        int eyr = Integer.parseInt(fields.get("eyr"));
        if (eyr < 2020 || eyr > 2030) return false;

        String hgt = fields.get("hgt");
        if (hgt.endsWith("cm")) {
            int parsed = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            if (parsed < 150 || parsed > 193) return false;
        } else if (hgt.endsWith("in")) {
            int parsed = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            if (parsed < 59 || parsed > 76) return false;
        } else {
            return false;
        }

        String hcl = fields.get("hcl");
        String regex = "^#([0-9a-f]{6})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(hcl);
        if (!matcher.matches()) return false;

        String ecl = fields.get("ecl");
        Set<String> eyeColors = new HashSet<>(List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
        if (!eyeColors.contains(ecl)) return false;

        String pid = fields.get("pid");
        regex = "^\\d{9}$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(pid);
        return matcher.matches();
    }

    public static String readInput(String name) {
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