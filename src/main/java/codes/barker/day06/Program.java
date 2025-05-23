package codes.barker.day06;

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
    public static void main(String[] args) {
        int answer = solve();
        System.out.println(answer);
    }

    private static int solve() {
        String input = readInput("day06.txt");
        String[] groups = input.split("\\r\\n\\r\\n");

        int sum = 0;

        for (String group : groups) {
            String[] answers = String.join("", group.split("\\r\\n")).split("");
            List<String> answerList = Arrays.asList(answers);
            Set<String> answerSet = new HashSet<>(answerList);
            sum += answerSet.size();
        }

        return sum;
    }

    private static String readInput(String name) {
        try (
                InputStream inputStream = Program.class.getClassLoader().getResourceAsStream(name);
                var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                var bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            return bufferedReader.lines().collect(Collectors.joining("\r\n"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
