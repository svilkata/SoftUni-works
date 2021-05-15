import java.util.Scanner;

public class IntersectionTwoMatrices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = Integer.parseInt(sc.nextLine());
        int cols = Integer.parseInt(sc.nextLine());

        char[][] first = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] line = sc.nextLine().split("\\s+");
            for (int col = 0; col < cols; col++) {
                first[row][col] = line[col].charAt(0);
            }
        }

        char[][] second = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            String[] line = sc.nextLine().split("\\s+");
            for (int col = 0; col < cols; col++) {
                second[row][col] = line[col].charAt(0);
            }
        }

        char[][] outMatrix = new char[rows][cols];

        for (int row = 0; row < first.length; row++) {
            char[] firstArr = first[row];
            char[] secondArr = second[row];

            for (int col = 0; col < firstArr.length; col++) {
                char symbol = firstArr[col] == secondArr[col]? firstArr[col] : '*';
                outMatrix[row][col] = symbol;
            }
        }

        for (int row = 0; row < outMatrix.length; row++) {
            for (int col = 0; col < outMatrix[row].length; col++) {
                System.out.print(outMatrix[row][col] + " ");
            }
            System.out.println();
        }

    }
}
