package codes.barker.day07;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        int answer = solve();
        System.out.println(answer);

        int answer2 = solve2();
        System.out.println(answer2);
    }

    private static int solve() {
        List<String> input = readInput();
        Map<String, Map<String, Integer>> parsed = parseInput(input);
        Map<String, Boolean> memo = new HashMap<>();

        int count = 0;

        for (String color : parsed.keySet()) {
            if (isMatch(color, parsed, memo)) {
                count++;
            }
        }

        return count;
    }

    private static int solve2() {
        List<String> input = readInput();
        Map<String, Map<String, Integer>> parsed = parseInput(input);
        Map<String, Integer> memo = new HashMap<>();
        return countBags("shiny gold", parsed, memo);
    }


    private static boolean isMatch(
            String color,
            Map<String, Map<String, Integer>> parsed,
            Map<String, Boolean> memo
    ) {
        if (memo.containsKey(color)) return memo.get(color);
        Map<String, Integer> contents = parsed.get(color);

        if (contents.isEmpty()) {
            memo.put(color, false);
            return false;
        }

        if (contents.containsKey("shiny gold")) {
            memo.put(color, true);
            return true;
        }

        for (String innerColor : contents.keySet()) {
            if (isMatch(innerColor, parsed, memo)) {
                memo.put(color, true);
                return true;
            }
        }

        memo.put(color, false);
        return false;
    }

    private static int countBags(String color, Map<String, Map<String, Integer>> parsed, Map<String, Integer> memo) {
        if (memo.containsKey(color)) return memo.get(color);
        Map<String, Integer> contents = parsed.get(color);

        if (contents.isEmpty()) {
            memo.put(color, 0);
            return 0;
        }

        int total = 0;

        for (Map.Entry<String, Integer> entry : contents.entrySet()) {
            int quantity = entry.getValue();
            total += quantity + quantity * countBags(entry.getKey(), parsed, memo);
        }

        memo.put(color, total);
        return total;
    }


    private static Map<String, Map<String, Integer>> parseInput(List<String> input) {
        Map<String, Map<String, Integer>> parsed = new HashMap<>();

        for (String line : input) {
            String[] temp = line.split(" bags contain ");

            String color = temp[0];
            String contents = temp[1];

            parsed.put(color, new HashMap<>());
            if (contents.startsWith("no other bags")) continue;

            String[] items = contents.substring(0, contents.length() - 1).split(", ");

            for (String item : items) {
                temp = item.split(" ");

                String quantity = temp[0];
                String[] colorParts = Arrays.copyOfRange(temp, 1, temp.length - 1);

                String innerColor = String.join(" ", colorParts);
                parsed.get(color).put(innerColor, Integer.parseInt(quantity));
            }
        }

        return parsed;
    }

    private static List<String> readInput() {
        try (
                InputStream inputStream = codes.barker.day07.Program.class.getClassLoader().getResourceAsStream("day07.txt");
                var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                var bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            return bufferedReader.lines().toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
