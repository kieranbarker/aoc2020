package codes.barker.day02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        int answer = solve();
        System.out.println(answer);

        int answer2 = solve2();
        System.out.println(answer2);
    }

    public static int solve() {
        List<String> input = readInput();
        int validPasswords = 0;

        for (String line : input) {
            HashMap<String, Object> parsed = parseLine(line);
            String password = (String) parsed.get("password");
            String letter = (String) parsed.get("letter");
            int min = (int) parsed.get("min");
            int max = (int) parsed.get("max");

            if (isValidPassword(password, letter, min, max)) {
                validPasswords++;
            }
        }

        return validPasswords;
    }

    public static int solve2() {
        List<String> input = readInput();
        int validPasswords = 0;

        for (String line : input) {
            HashMap<String, Object> parsed = parseLine(line);
            String password = (String) parsed.get("password");
            String letter = (String) parsed.get("letter");
            int min = (int) parsed.get("min");
            int max = (int) parsed.get("max");

            if (isValidPassword2(password, letter, min, max)) {
                validPasswords++;
            }
        }

        return validPasswords;
    }

    public static boolean isValidPassword(String password, String target, int min, int max) {
        String[] letters = password.split("");
        int count = 0;

        for (String letter : letters) {
            if (letter.equals(target)) {
                count++;
            }
        }

        return count >= min && count <= max;
    }

    public static boolean isValidPassword2(String password, String target, int i, int j) {
        String[] letters = password.split("");
        return letters[i - 1].equals(target) ^ letters[j - 1].equals(target);
    }

    public static HashMap<String, Object> parseLine(String line) {
        var parsed = new HashMap<String, Object>();

        String[] temp = line.split(": ");
        String passwordPolicy = temp[0];
        String password = temp[1];
        parsed.put("password", password);

        temp = passwordPolicy.split(" ");
        String range = temp[0];
        String letter = temp[1];
        parsed.put("letter", letter);

        temp = range.split("-");
        parsed.put("min", Integer.parseInt(temp[0]));
        parsed.put("max", Integer.parseInt(temp[1]));

        return parsed;
    }

    public static List<String> readInput() {
        try (
                InputStream inputStream = Program.class.getClassLoader().getResourceAsStream("day02.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            return bufferedReader.lines().toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
