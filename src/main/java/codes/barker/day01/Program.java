package codes.barker.day01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        int answer = solve();
        System.out.println(answer);

        int answer2 = solve2();
        System.out.println(answer2);
    }

    private static int solve() {
        List<String> input = readInput();
        int[] entries = input.stream().mapToInt(Integer::valueOf).toArray();

        int x = 0;
        int y = 0;

        outer:
        for (int i = 0; i < entries.length; i++) {
            for (int j = i + 1; j < entries.length; j++) {
                if (entries[i] + entries[j] == 2020) {
                    x = entries[i];
                    y = entries[j];
                    break outer;
                }
            }
        }

        return x * y;
    }

    private static int solve2() {
        List<String> input = readInput();
        int[] entries = input.stream().mapToInt(Integer::valueOf).toArray();

        int x = 0;
        int y = 0;
        int z = 0;

        outer:
        for (int i = 0; i < entries.length; i++) {
            for (int j = i + 1; j < entries.length; j++) {
                for (int k = j + 1; k < entries.length; k++) {
                    if (entries[i] + entries[j] + entries[k] == 2020) {
                        x = entries[i];
                        y = entries[j];
                        z = entries[k];
                        break outer;
                    }
                }
            }
        }

        return x * y * z;
    }

    private static List<String> readInput() {
        try (
                InputStream inputStream = Program.class.getClassLoader().getResourceAsStream("day01.txt");
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