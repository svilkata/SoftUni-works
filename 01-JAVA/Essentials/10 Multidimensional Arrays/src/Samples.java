import java.util.Arrays;
import java.util.Scanner;

public class Samples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] intMatrix = new int[3][2];

        //System.out.println(Arrays.toString(intMatrix[0]));

        int[][] array = new int[][]{{1, 2, 3, 4, 5, 6, 7}, {1, 2, 3, 4}};


        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                //array[row][col] = row + col;
                System.out.print(array[row][col]+ ",");
            }
            System.out.println();
        }
    }
}
