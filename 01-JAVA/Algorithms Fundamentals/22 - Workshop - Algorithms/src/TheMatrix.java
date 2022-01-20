import java.util.Arrays;
import java.util.Scanner;

public class TheMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] dimensions = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        
        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = sc.nextLine().replaceAll("\\s+", "").toCharArray();
        }

        char color = sc.nextLine().charAt(0);
        dimensions = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int startRow = dimensions[0];
        int startCol = dimensions[1];
        char oldColor = matrix[startRow][startCol];

        colorPlane(matrix, startRow, startCol, color, oldColor);



        printMatrix(matrix);
        
        
    }

    private static void colorPlane(char[][] matrix, int row, int col, char color, char oldColor) {
        if (isOutOfBounds(matrix, row, col)) {
            return;
        }

        if (matrix[row][col] != oldColor) {
            return;
        }

        matrix[row][col] = color;

        colorPlane(matrix, row + 1, col, color, oldColor);
        colorPlane(matrix, row - 1, col, color, oldColor);
        colorPlane(matrix, row, col + 1, color, oldColor);
        colorPlane(matrix, row, col - 1, color, oldColor);

    }

    private static boolean isOutOfBounds(char[][] matrix, int row, int col) {
        return row >= matrix.length || row < 0 || col >= matrix[row].length || col < 0;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char e : arr) {
                System.out.print(e);
            }
            System.out.println();
        }
    }
}
