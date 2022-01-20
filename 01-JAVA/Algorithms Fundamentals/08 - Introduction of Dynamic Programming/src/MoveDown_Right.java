import java.util.*;

public class MoveDown_Right {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = Integer.parseInt(sc.nextLine());
        int cols = Integer.parseInt(sc.nextLine());

        int[][] elements = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            elements[row] = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] dpTable = new int[rows][cols];

        dpTable[0][0] = elements[0][0];

        for (int col = 1; col < cols; col++) {
            dpTable[0][col] = dpTable[0][col - 1] + elements[0][col];
        }

        for (int row = 1; row < rows; row++) {
            dpTable[row][0] = dpTable[row - 1][0] + elements[row][0];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                dpTable[row][col] = Math.max(dpTable[row - 1][col] + elements[row][col],
                        dpTable[row][col - 1] + elements[row][col]);
            }
        }

        int row = rows - 1;
        int col = cols - 1;

        List<String> path = new ArrayList<>();

        path.add(formatOutput(row, col));
        while (row > 0 || col > 0) {
            int top = -1;
            if (row > 0) {
                top = dpTable[row - 1][col];
            }

            int left = -1;
            if (col > 0) {
                left = dpTable[row][col - 1];
            }

            if (top > left) {
                row--;
            } else {
                col--;
            }
            path.add(formatOutput(row, col));
        }

        Collections.reverse(path);
        System.out.println(String.join(" ", path));
    }

    private static String formatOutput(int row, int col) {
        return "[" + row + ", " + col + "]";
    }
}
