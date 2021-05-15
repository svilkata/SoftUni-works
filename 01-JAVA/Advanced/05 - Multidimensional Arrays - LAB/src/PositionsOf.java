import java.util.Arrays;
import java.util.Scanner;

public class PositionsOf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = sc.nextInt();
            }
        }

        int number = sc.nextInt();
        String output = "";

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == number) {
                    output+= row + " " + col + "\r\n";
                }
            }
        }

        if (output.equals("")) {
            System.out.println("not found");
        } else {
            System.out.println(output);
        }


    }

}
