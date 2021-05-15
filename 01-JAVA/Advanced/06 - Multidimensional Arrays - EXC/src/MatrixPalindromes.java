import java.util.Arrays;
import java.util.Scanner;

public class MatrixPalindromes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(x -> Integer.parseInt(x)).toArray();
        int rows = arr[0];
        int cols = arr[1];

        String[][] matrix = new String[rows][cols];

        char rowFirstAndThirdLetter = 'a';
        char colSecondLetter = 'a';
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = String.valueOf(rowFirstAndThirdLetter) + String.valueOf(colSecondLetter++)
                        + String.valueOf(rowFirstAndThirdLetter);
            }
            rowFirstAndThirdLetter++;
            colSecondLetter = rowFirstAndThirdLetter;
        }

        printMatrix(matrix);


    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                //array[row][col] = row + col;
                System.out.print(matrix[row][col]+ " ");
            }
            System.out.println();
        }

    }
}
