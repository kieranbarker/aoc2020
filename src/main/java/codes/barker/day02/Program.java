package codes.barker.day02;

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

    public static int solve() {
        List<String> input = readInput();
        int validLines = 0;

        for (String line : input) {
            Line parsed = parseLine(line);

            if (isValidLine(parsed)) {
                validLines++;
            }
        }

        return validLines;
    }

    public static int solve2() {
        List<String> input = readInput();
        int validLines = 0;

        for (String line : input) {
            Line2 parsed = parseLine2(line);

            if (isValidLine2(parsed)) {
                validLines++;
            }
        }

        return validLines;
    }

    public static boolean isValidLine(Line line) {
        String[] letters = line.password().split("");
        int count = 0;

        for (String letter : letters) {
            if (letter.equals(line.letter())) {
                count++;
            }
        }

        return count >= line.min() && count <= line.max();
    }

    public static boolean isValidLine2(Line2 line) {
        String[] letters = line.password().split("");
        String letter = line.letter();

        int i = line.i();
        int j = line.j();

        return letters[i].equals(letter) ^ letters[j].equals(letter);
    }

    public static Line parseLine(String line) {
        String[] temp = line.split(": ");
        String passwordPolicy = temp[0];
        String password = temp[1];

        temp = passwordPolicy.split(" ");
        String range = temp[0];
        String letter = temp[1];

        temp = range.split("-");
        int min = Integer.parseInt(temp[0]);
        int max = Integer.parseInt(temp[1]);

        return new Line(password, letter, min, max);
    }

    public static Line2 parseLine2(String line) {
        String[] temp = line.split(": ");
        String passwordPolicy = temp[0];
        String password = temp[1];

        temp = passwordPolicy.split(" ");
        String range = temp[0];
        String letter = temp[1];

        temp = range.split("-");
        int i = Integer.parseInt(temp[0]) - 1;
        int j = Integer.parseInt(temp[1]) - 1;

        return new Line2(password, letter, i, j);
    }

    public static List<String> readInput() {
        try (
            InputStream inputStream = Program.class.getClassLoader().getResourceAsStream("day02.txt");
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
