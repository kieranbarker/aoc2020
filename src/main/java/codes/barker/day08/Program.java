package codes.barker.day08;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        int answer = solve();
        System.out.println(answer);

        Integer answer2 = solve2();
        System.out.println(answer2);
    }

    private static int solve() {
        List<String> input = readInput();
        List<Instruction> instructions = parseInput(input);
        return runProgram(instructions);
    }

    private static Integer solve2() {
        List<String> input = readInput();
        List<Instruction> instructions = parseInput(input);
        List<Integer> indices = findSwappableIndices(instructions);

        for (int i : indices) {
            List<Instruction> modified = getInstructions(i, instructions);
            Result result = runProgram2(modified);
            if (result.terminatedNormally()) return result.accumulator();
        }

        return null;
    }

    private static int runProgram(List<Instruction> instructions) {
        Set<Integer> visited = new HashSet<>();

        int index = 0;
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

    private static Result runProgram2(List<Instruction> instructions) {
        Set<Integer> visited = new HashSet<>();

        int index = 0;
        int accumulator = 0;

        while (true) {
            if (index == instructions.size()) {
                return new Result(accumulator, true);
            }

            if (visited.contains(index)) {
                return new Result(accumulator, false);
            }

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
    }

    private static List<Instruction> getInstructions(int i, List<Instruction> instructions) {
        List<Instruction> modified = new ArrayList<>(instructions);
        Instruction original = modified.get(i);

        String originalOperation = original.operation();
        String newOperation = switch (originalOperation) {
            case "jmp" -> "nop";
            case "nop" -> "jmp";
            default -> throw new IllegalStateException("Unexpected operation: " + originalOperation);
        };

        Instruction swapped = new Instruction(newOperation, original.argument());
        modified.set(i, swapped);

        return modified;
    }

    private static List<Integer> findSwappableIndices(List<Instruction> instructions) {
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < instructions.size(); i++) {
            String operation = instructions.get(i).operation();

            if (operation.equals("jmp") || operation.equals("nop")) {
                indices.add(i);
            }
        }

        return indices;
    }

    private static List<Instruction> parseInput(List<String> input) {
        return input.stream().map(line -> {
            String[] parts = line.split(" ");
            return new Instruction(parts[0], Integer.parseInt(parts[1]));
        }).toList();
    }

    private static List<String> readInput() {
        try (
                InputStream inputStream = Program.class.getClassLoader().getResourceAsStream("day08.txt");
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
