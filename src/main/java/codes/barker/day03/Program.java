package codes.barker.day03;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        int answer = solve();
        System.out.println(answer);
    }

    public static int solve() {
        List<String> input = readInput();
        String[][] grid = parseInput(input);

        int rows = grid.length;
        int cols = grid[0].length;

        int i = 0;
        int j = 0;

        int trees = 0;

        while (true) {
            j += 3;

            if (j >= cols) {
                j -= cols;
            }

            i++;

            if (i >= rows) {
                break;
            }

            if (grid[i][j].equals("#")) {
                trees++;
            }
        }

        return trees;
    }

    public static String[][] parseInput(List<String> input) {
        int rows = input.size();
        int cols = input.getFirst().length();

        String[][] grid = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = input.get(i).split("");
        }

        return grid;
    }

    public static List<String> readInput() {
        try (
                InputStream inputStream = Program.class.getClassLoader().getResourceAsStream("day03.txt");
                var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                var bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            return bufferedReader.lines().toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
