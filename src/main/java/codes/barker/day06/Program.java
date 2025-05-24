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

        int answer2 = solve2();
        System.out.println(answer2);
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

    private static int solve2() {
        String input = readInput("day06.txt");
        String[] groups = input.split("\\r\\n\\r\\n");

        int sum = 0;

        for (String group : groups) {
            String[] people = group.split("\\r\\n");
            Set<String> set = new HashSet<>(Arrays.asList(people[0].split("")));

            for (int i = 1; i < people.length; i++) {
                String[] answers = people[i].split("");
                List<String> answerList = Arrays.asList(answers);
                Set<String> answerSet = new HashSet<>(answerList);
                set.retainAll(answerSet);

            }

            sum += set.size();
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
