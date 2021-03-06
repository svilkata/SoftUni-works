import java.util.Scanner;

public class FillIt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(", ");
        int n = Integer.parseInt(input[0]);

        int[][] matrix = new int[n][n];

        int counter = 1;
        if (input[1].equals("A")) {
            for (int col = 0; col < matrix[0].length; col++) {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][col] = counter++;
                }
            }
        } else if (input[1].equals("B")) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (col % 2 == 0) {
                    for (int row = 0; row < matrix.length; row++) {
                        matrix[row][col] = counter++;
                    }
                } else {
                    for (int row = matrix.length -1; row >= 0; row--) {
                        matrix[row][col] = counter++;
                    }
                }
            }
        }

        //print the matrix
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
