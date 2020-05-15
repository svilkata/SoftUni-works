import java.util.Arrays;
import java.util.Scanner;

public class MaxSum2x2Submatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] sizes = Arrays.stream(sc.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = sizes[0];
        int cols = sizes[1];

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(sc.nextLine().split(", "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int maxSUm = Integer.MIN_VALUE;
        int[][] maxMatrix = new int[2][2];

        for (int r = 0; r < matrix.length - 1; r++) {
            for (int c = 0; c < matrix[r].length - 1; c++) {
                int current = matrix[r][c];
                int right = matrix[r][c+1];
                int below = matrix[r+1][c];
                int diagonal = matrix[r+1][c+1];

                int sum = current + right + below + diagonal;
                if (sum > maxSUm) {
                    maxSUm = sum;
                    maxMatrix[0][0] = current;
                    maxMatrix[0][1] = right;
                    maxMatrix[1][0] = below;
                    maxMatrix[1][1] = diagonal;
                }
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(maxMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(maxSUm);
    }
}
