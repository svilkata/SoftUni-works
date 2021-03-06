import java.util.Arrays;
import java.util.Scanner;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] sizes = Arrays.stream(sc.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = sizes[0];
        int cols = sizes[1];

        int sum = 0;
        int[][] matrix = readMatrix(sc, rows, cols, ", ");
        for (int[] arr : matrix) {
            for (int num : arr) {
                sum+= num;
            }
        }

        System.out.println(rows);
        System.out.println(cols);
        System.out.println(sum);



    }

    private static int[][] readMatrix(Scanner sc, int rows, int cols, String pattern) {
        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int[] line = Arrays.stream(sc.nextLine().split(pattern))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = line[col];
            }
        }

        return matrix;
    }
}
