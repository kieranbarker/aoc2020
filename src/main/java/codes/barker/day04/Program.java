package codes.barker.day04;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Program {
    private static final String input = readInput("day04.txt");
    private static final String[] passports = input.split("\\n\\n");
    private static final Set<String> requiredFields = new HashSet<>(List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));

    public static void main(String[] args) {
        long answer = solve();
        System.out.println(answer);
    }

    public static long solve() {
        return Arrays.stream(passports).filter(Program::isValidPassport).count();
    }

    public static boolean isValidPassport(String passport) {
        Set<String> fields = Arrays.stream(passport.split("\\s+"))
                .map(pair -> pair.split(":")[0])
                .collect(Collectors.toSet());
        return fields.containsAll(requiredFields);
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
