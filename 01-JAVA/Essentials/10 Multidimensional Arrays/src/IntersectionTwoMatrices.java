import java.util.Scanner;

public class IntersectionTwoMatrices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = Integer.parseInt(sc.nextLine());
        int cols = Integer.parseInt(sc.nextLine());

        char[][] firstMatrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < cols; j++) {
                firstMatrix[i][j] = line[j].charAt(0);
            }
        }

        char[][] secondMatrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < cols; j++) {
                secondMatrix[i][j] = line[j].charAt(0);
            }
        }

        for (int r = 0; r < firstMatrix.length; r++) {
            for (int c = 0; c < firstMatrix[r].length; c++) {
                if (firstMatrix[r][c] != secondMatrix[r][c]) {
                    firstMatrix[r][c] = '*';
                }
            }
        }


        for (int r = 0; r < firstMatrix.length; r++) {
            for (int c = 0; c < firstMatrix[r].length; c++) {
                System.out.print(firstMatrix[r][c] + " ");
            }
            System.out.println();
        }


    }
}
