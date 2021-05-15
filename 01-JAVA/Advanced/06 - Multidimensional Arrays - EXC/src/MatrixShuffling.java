import java.util.Arrays;
import java.util.Scanner;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dimensions = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];
        String[][] matrix = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = sc.nextLine().split("\\s+");
        }

        String line = "";

        while (!(line = sc.nextLine()).equals("END")) {
            String[] tokens = line.split("\\s+");
            if (!tokens[0].equals("swap")) {
                System.out.println("Invalid input!");
                continue;
            }

            if (tokens.length != 5) {
                System.out.println("Invalid input!");
                continue;
            }

            int row1 = Integer.parseInt(tokens[1]);
            int col1 = Integer.parseInt(tokens[2]);
            int row2 = Integer.parseInt(tokens[3]);
            int col2 = Integer.parseInt(tokens[4]);

            if (row1 < 0 || row1 > matrix.length - 1 || col1 < 0 || col1 > matrix[0].length - 1 ||
                    row2 < 0 || row2 > matrix.length - 1 || col2 < 0 || col2 > matrix[0].length - 1) {
                System.out.println("Invalid input!");
                continue;
            }

            String temp = matrix[row1][col1];
            matrix[row1][col1] = matrix[row2][col2];
            matrix[row2][col2] = temp;

            printMatrix(matrix);

        }


    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                //array[row][col] = row + col;
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

    }
}
