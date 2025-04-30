package codes.barker.day01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Day01 {
    public static void main(String[] args) {
        int part01 = solvePart01();
        System.out.println(part01);

        int part02 = solvePart02();
        System.out.println(part02);
    }

    public static int solvePart01() {
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

    public static int solvePart02() {
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

    public static List<String> readInput() {
        try (
                var inputStream = Day01.class.getClassLoader().getResourceAsStream("day01.txt");
                var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                var reader = new BufferedReader(inputStreamReader);
        ) {
            return reader.lines().toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}