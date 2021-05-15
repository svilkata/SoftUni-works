import java.util.Scanner;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dimenInput = sc.nextLine().split(", ");
        int rows = Integer.parseInt(dimenInput[0]);
        int cols = Integer.parseInt(dimenInput[1]);

        int[][] matr = readMatrix(rows, cols, sc);
        System.out.println(matr.length);
        System.out.println(matr[0].length);

        int sum = 0;
        for (int row = 0; row < matr.length; row++) {
            for (int col = 0; col < matr[row].length; col++) {
                sum+= matr[row][col];
            }
        }

        System.out.println(sum);






    }

    private static int[][] readMatrix(int rows, int col, Scanner sc) {
        int[][] martix = new int[rows][col];

        for (int r = 0; r < rows; r++) {
            String[] elements = sc.nextLine().split(", ");

            for (int c = 0; c < elements.length; c++) {

                martix[r][c] = Integer.parseInt(elements[c]);
            }
        }

        return martix;
    }
}
