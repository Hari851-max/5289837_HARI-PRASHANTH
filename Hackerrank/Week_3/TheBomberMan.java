import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

  public static List<String> bomberMan(int n, List<String> grid) {
        int R = grid.size();
        int C = grid.get(0).length();

        // Convert List<String> to char[][] for easier manipulation
        char[][] currentGrid = new char[R][C];
        for (int i = 0; i < R; i++) {
            currentGrid[i] = grid.get(i).toCharArray();
        }

        // Base case: n = 1, return initial grid
        if (n == 1) {
            return grid;
        }

        // Even seconds: All cells are filled with bombs
        if (n % 2 == 0) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < R; i++) {
                char[] row = new char[C];
                Arrays.fill(row, 'O');
                result.add(new String(row));
            }
            return result;
        }

        // For odd seconds > 1, the pattern repeats every 4 seconds after n=3
        // Effectively, we only need to simulate for n=3 and n=5 (which corresponds to n%4==1)
        // because n=7 will be same as n=3, n=9 same as n=5, and so on.

        char[][] after1stDetonation = simulateDetonation(currentGrid); // This is the state after 3 seconds
        char[][] tempGridFor5s = new char[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(tempGridFor5s[i], 'O'); // Grid full of bombs before the 5s detonation
        }
        char[][] after2ndDetonation = simulateDetonation(tempGridFor5s, after1stDetonation); // This is the state after 5 seconds

        if ((n % 4) == 3) { // Corresponds to n=3, 7, 11...
            return convertCharGridToStringList(after1stDetonation);
        } else { // Corresponds to n=5, 9, 13... (n%4==1 when n>1)
            return convertCharGridToStringList(after2ndDetonation);
        }
    }

    // Helper function to simulate bomb detonation
    private static char[][] simulateDetonation(char[][] prevGrid) {
        int R = prevGrid.length;
        int C = prevGrid[0].length;
        char[][] nextGrid = new char[R][C];

        // Initially fill the nextGrid with 'O' (new bombs planted)
        for (int i = 0; i < R; i++) {
            Arrays.fill(nextGrid[i], 'O');
        }

        // Detonate bombs from prevGrid
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (prevGrid[r][c] == 'O') {
                    // This bomb detonates, clear its cell and neighbors in nextGrid
                    nextGrid[r][c] = '.';
                    if (r > 0) nextGrid[r - 1][c] = '.';
                    if (r < R - 1) nextGrid[r + 1][c] = '.';
                    if (c > 0) nextGrid[r][c - 1] = '.';
                    if (c < C - 1) nextGrid[r][c + 1] = '.';
                }
            }
        }
        return nextGrid;
    }

    // Overloaded simulateDetonation for the case of 5 seconds (initial bombs + new bombs)
    private static char[][] simulateDetonation(char[][] currentBombs, char[][] bombsToDetonate) {
        int R = currentBombs.length;
        int C = currentBombs[0].length;
        char[][] nextGrid = new char[R][C];

        // Copy currentBombs (all 'O's at time 4) to nextGrid
        for (int i = 0; i < R; i++) {
            System.arraycopy(currentBombs[i], 0, nextGrid[i], 0, C);
        }

        // Detonate bombs from bombsToDetonate
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (bombsToDetonate[r][c] == 'O') {
                    nextGrid[r][c] = '.';
                    if (r > 0) nextGrid[r - 1][c] = '.';
                    if (r < R - 1) nextGrid[r + 1][c] = '.';
                    if (c > 0) nextGrid[r][c - 1] = '.';
                    if (c < C - 1) nextGrid[r][c + 1] = '.';
                }
            }
        }
        return nextGrid;
    }

    // Helper function to convert char[][] to List<String>
    private static List<String> convertCharGridToStringList(char[][] grid) {
        List<String> result = new ArrayList<>();
        for (char[] row : grid) {
            result.add(new String(row));
        }
        return result;
}
    

    }



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
