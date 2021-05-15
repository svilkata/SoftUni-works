import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] rowsAndCols = Arrays.stream(sc.nextLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();
        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];
        int[][] firstMatrix = readMatrix(rows, cols, sc);

        rowsAndCols = Arrays.stream(sc.nextLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();
        rows = rowsAndCols[0];
        cols = rowsAndCols[1];
        int[][] secondMatrix = readMatrix(rows, cols, sc);

        if (areMatrixEqual(firstMatrix, secondMatrix)) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }

       // System.out.println();


    }

    private static boolean areMatrixEqual(int[][] firstMatrix, int[][] secondMatrix) {
        boolean areEqual = true;

        if (firstMatrix.length != secondMatrix.length) {
            areEqual = false;
        } else {
            for (int r = 0; r < firstMatrix.length; r++) {
                int[] firstArray = firstMatrix[r];
                int[] secondArray = secondMatrix[r];
                if (firstArray.length != secondArray.length) {
                    areEqual = false;
                    break;
                } else {
                    for (int c = 0; c < firstArray.length; c++) {
                        if (firstArray[c] != secondArray[c]) {
                            return false;
                        }

                    }
                }
            }
        }


        return areEqual;
    }

    private static int[][] readMatrix(int rows, int col, Scanner sc) {
        int[][] martix = new int[rows][col];
        for (int r = 0; r < rows; r++) {
            String[] elements = sc.nextLine().split(" ");
            for (int c = 0; c < elements.length; c++) {
                int number = Integer.parseInt(elements[c]);
                martix[r][c] = number;
            }
        }

        return martix;
    }
}
