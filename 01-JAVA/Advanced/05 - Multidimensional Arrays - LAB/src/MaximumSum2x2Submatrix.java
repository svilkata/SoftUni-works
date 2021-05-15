import java.util.Arrays;
import java.util.Scanner;

public class MaximumSum2x2Submatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] inputDimensions = Arrays.stream(sc.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int rows = inputDimensions[0];
        int cols = inputDimensions[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] line = sc.nextLine().split(", ");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(line[col]);
            }
        }

        int[][] twoByTwoMatrix = new int[2][2];

        int row = 0, col = 0;
        int maxSum = 0;

        while (row >= 0 && row < rows && col >= 0 && col < cols) {
            int sum = matrix[row][col] + matrix[row + 1][col] + matrix[row][col + 1] + matrix[row + 1][col + 1];

            if (sum > maxSum) {
                maxSum = sum;

                twoByTwoMatrix[0][0] = matrix[row][col];
                twoByTwoMatrix[0][1] = matrix[row][col + 1];
                twoByTwoMatrix[1][0] = matrix[row + 1][col];
                twoByTwoMatrix[1][1] = matrix[row + 1][col + 1];
            }

            col++;
            if (col == cols - 1) {
                row++;
                col = 0;
            }
            if (row == rows - 1) {
                break;
            }
        }

        printMatrix2_2(twoByTwoMatrix);
        System.out.println(maxSum);
    }

    private static void printMatrix2_2(int[][] twoByTwoMatrix) {
           System.out.println(twoByTwoMatrix[0][0] + " " + twoByTwoMatrix[0][1]);
           System.out.println(twoByTwoMatrix[1][0] + " " + twoByTwoMatrix[1][1]);

    }
}
