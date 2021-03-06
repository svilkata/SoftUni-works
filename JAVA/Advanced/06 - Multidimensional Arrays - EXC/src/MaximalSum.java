import java.util.Arrays;
import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] sizeMatrix = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rowsN = sizeMatrix[0];
        int colsM = sizeMatrix[1];
        int[][] matrix = new int[rowsN][colsM];

        readMatrix(sc, rowsN, colsM, matrix);

        int maxSum = Integer.MIN_VALUE;
        int[] coordinatesBestSumStart3x3 = {0, 0};

        for (int row = 0; row <= matrix.length - 3; row++) {
            for (int col = 0; col <= matrix[0].length - 3; col++) {
                int currSum = sum3x3Matrix(matrix, row, col);
                if (currSum > maxSum) {
                    maxSum = currSum;
                    coordinatesBestSumStart3x3[0] = row;
                    coordinatesBestSumStart3x3[1] = col;
                }
            }
        }

        System.out.println("Sum = " + maxSum);
        printMatrix3x3BestSum(matrix, coordinatesBestSumStart3x3[0], coordinatesBestSumStart3x3[1]);

        System.out.println();

    }

    private static int sum3x3Matrix(int[][] matrix, int row, int col) {
        int sum = 0;
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    private static void readMatrix(Scanner sc, int rowsN, int colsM, int[][] matrix) {
        for (int row = 0; row < rowsN; row++) {
            matrix[row] = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(x -> Integer.parseInt(x)).toArray();
        }
    }

    private static void printMatrix3x3BestSum(int[][] matrix, int rowStart, int colStart) {
        for (int row = rowStart; row < rowStart + 3; row++) {
            for (int col = colStart; col < colStart + 3; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
