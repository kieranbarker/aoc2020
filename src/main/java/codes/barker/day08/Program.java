package codes.barker.day08;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        int answer = solve();
        System.out.println(answer);
    }

    private static int solve() {
        List<String> input = readInput();
        List<Instruction> instructions = parseInput(input);

        int index = 0;
        Set<Integer> visited = new HashSet<>();

        int accumulator = 0;

        while (!visited.contains(index)) {
            visited.add(index);
            var instruction = instructions.get(index);

            switch (instruction.operation()) {
                case "acc" -> {
                    accumulator += instruction.argument();
                    index++;
                }
                case "jmp" -> index += instruction.argument();
                case "nop" -> index++;
            }
        }

        return accumulator;
    }

    private static List<Instruction> parseInput(List<String> input) {
        return input.stream().map(line -> {
            String[] parts = line.split(" ");
            return new Instruction(parts[0], Integer.parseInt(parts[1]));
        }).toList();
    }

    private static List<String> readInput() {
        try (
                InputStream inputStream = codes.barker.day08.Program.class.getClassLoader().getResourceAsStream("day08.txt");
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
