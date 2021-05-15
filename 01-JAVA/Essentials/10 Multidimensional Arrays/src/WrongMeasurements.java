import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WrongMeasurements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = Integer.parseInt(sc.nextLine());

        int[][] matrix = new int[rows][];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int r = sc.nextInt();
        int c = sc.nextInt();

        int wrongValue = matrix[r][c];

        ArrayList<Integer> values = new ArrayList<Integer>();
        ArrayList<int[]> indexes = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == wrongValue) {
                    int validValue = getValidValue(matrix, i, j, wrongValue);
                    values.add(validValue);
                    indexes.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < values.size(); i++) {
            matrix[indexes.get(i)[0]][indexes.get(i)[1]] = values.get(i);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }


    }

    private static int getValidValue(int[][] matrix, int row, int col, int wrongValue) {
        int sum = 0;

        if (isInBounds(row + 1, col, matrix) && matrix[row + 1][col] != wrongValue) {
            sum += matrix[row + 1][col];
        }

        if (isInBounds(row - 1, col, matrix) && matrix[row - 1][col] != wrongValue) {
            sum += matrix[row - 1][col];
        }

        if (isInBounds(row, col + 1, matrix) && matrix[row][col + 1] != wrongValue) {
            sum += matrix[row][col + 1];
        }

        if (isInBounds(row, col - 1, matrix) && matrix[row][col - 1] != wrongValue) {
            sum += matrix[row][col - 1];
        }


        return sum;


    }

    private static boolean isInBounds(int r, int c, int[][] matrix) {
        return r >= 0 && r < matrix.length && c >= 0 && c < matrix[r].length;
    }
}
