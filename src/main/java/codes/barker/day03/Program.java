package codes.barker.day03;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Program {
    private static final List<String> input = readInput("day03.txt");
    private static final String[][] grid = parseInput(input);

    private static final int rows = grid.length;
    private static final int cols = grid[0].length;

    public static void main(String[] args) {
        int answer = solve();
        System.out.println(answer);

        long answer2 = solve2();
        System.out.println(answer2);
    }

    public static int solve() {
        return countTrees(1, 3);
    }

    public static long solve2() {
        int[][] slopes = {
                {1, 1},
                {1, 3},
                {1, 5},
                {1, 7},
                {2, 1},
        };

        long product = 1;

        for (int[] slope : slopes) {
            product *= countTrees(slope[0], slope[1]);
        }

        return product;
    }

    public static int countTrees(int stepI, int stepJ) {
        int i = 0;
        int j = 0;

        int trees = 0;

        while (true) {
            j += stepJ;

            if (j >= cols) {
                j -= cols;
            }

            i += stepI;

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

    public static List<String> readInput(String name) {
        try (
                InputStream inputStream = Program.class.getClassLoader().getResourceAsStream(name);
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
