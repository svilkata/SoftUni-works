import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] dimensions = readRowsAndColumns(sc);
        int rows = dimensions[0];
        int cols = dimensions[1];
        int[][] firstMatrix = readMatrix(sc, rows, cols);

        dimensions = readRowsAndColumns(sc);
        rows = dimensions[0];
        cols = dimensions[1];
        int[][] secondMatrix = readMatrix(sc, rows, cols);

        boolean areEqual = compareMatrices(firstMatrix, secondMatrix);

        if (areEqual) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }

    }

    private static boolean compareMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length) {
            return false;
        }

        if (firstMatrix[0].length != secondMatrix[0].length) {
            return false;
        }

        for (int row = 0; row < firstMatrix.length; row++) {
            int[] arr = firstMatrix[row];
            for (int col = 0; col < arr.length; col++) {
                int firstElement = arr[col];
                int secondElement = secondMatrix[row][col];
                if (secondElement != firstElement) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int[][] readMatrix(Scanner sc, int rows, int cols) {
        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int[] line = Arrays.stream(sc.nextLine().split("\\s+"))
                    .mapToInt(c -> Integer.parseInt(c))
                    .toArray();
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = line[col];
            }
        }

        return matrix;
    }

    private static int[] readRowsAndColumns(Scanner sc) {
        return Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
