package codes.barker.day05;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.IntStream;

public class Program {
    public static void main(String[] args) {
        int answer = solve();
        System.out.println(answer);
    }

    private static int solve() {
        List<String> boardingPasses = readInput("day05.txt");
        IntStream seatIDs = boardingPasses.stream().mapToInt(Program::getSeatID);
        return seatIDs.max().orElseThrow();
    }

    private static int getRowNum(String row) {
        int low = 0;
        int high = 127;

        for (int i = 0; i < row.length(); i++) {
            char c = row.charAt(i);
            int mid = low + (high - low) / 2;

            if (c == 'F') {
                high = mid - 1;
            } else if (c == 'B') {
                low = mid + 1;
            }
        }

        return low;
    }

    private static int getColNum(String col) {
        int low = 0;
        int high = 7;

        for (int i = 0; i < col.length(); i++) {
            char c = col.charAt(i);
            int mid = low + (high - low) / 2;

            if (c == 'L') {
                high = mid - 1;
            } else if (c == 'R') {
                low = mid + 1;
            }
        }

        return low;
    }

    private static int getSeatID(int row, int col) {
        return row * 8 + col;
    }

    private static int getSeatID(String boardingPass) {
        String row = boardingPass.substring(0, 7);
        int rowNum = getRowNum(row);

        String col = boardingPass.substring(7);
        int colNum = getColNum(col);

        return getSeatID(rowNum, colNum);
    }

    private static List<String> readInput(String name) {
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
