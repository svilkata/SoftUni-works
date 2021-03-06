import java.util.Scanner;

public class FindTheRealQueen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[][] matrixSquareBy8 = new char[8][8];

        for (int row = 0; row < 8; row++) {
            String[] line = sc.nextLine().split("\\s+");
            for (int col = 0; col < 8; col++) {
                matrixSquareBy8[row][col] = line[col].charAt(0);
            }
        }


        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean isRealQueen = false;
                if (matrixSquareBy8[row][col] == 'q') {
                    isRealQueen = findRealQueen(row, col, matrixSquareBy8);
                    if (isRealQueen) {
                        break;
                    }
                }

            }
        }

    }

    private static boolean findRealQueen(int row, int col, char[][] matrix) {
        //хоризонтала
        boolean isThereHorizontalQueen = findHorizonatal(row, col, matrix);
        boolean isThereVerticalQueen = findVertical(row, col, matrix);
        boolean isThereDiagonalsQueen = findDiagonals(row, col, matrix);
        boolean isRealQueen = isThereHorizontalQueen && isThereVerticalQueen && isThereDiagonalsQueen;

        if (isRealQueen) {
            System.out.println(row + " " + col);
        }
        return isRealQueen;
    }

    private static boolean findDiagonals(int row, int col, char[][] matrix) {
        int countRow = row;
        int countCol = col;

        //главен диагонал  - вляво и нагоре
        while (countRow >= 1 && countCol >= 1) {
            countRow--;
            countCol--;
            if (matrix[countRow][countCol] == 'q') {
                return false;
            }
        }

        //главен диагонал  - вдясно и надолу
        countRow = row;
        countCol = col;
        while (countRow <= 6 && countCol <= 6) {
            countRow++;
            countCol++;
            if (matrix[countRow][countCol] == 'q') {
                return false;
            }
        }

        //вторичен диагонал  - вдясно и нагоре
        countRow = row;
        countCol = col;
        while (countRow >= 1 && countCol <= 6) {
            countRow--;
            countCol++;
            if (matrix[countRow][countCol] == 'q') {
                return false;
            }
        }

        //вторичен диагонал  - вляво и надолу
        countRow = row;
        countCol = col;
        while (countRow <=6 && countCol >= 1) {
            countRow++;
            countCol--;
            if (matrix[countRow][countCol] == 'q') {
                return false;
            }
        }

        return true;
    }

    private static boolean findVertical(int row, int col, char[][] matrix) {
        //нагоре
        int countRow = row;
        while (countRow >= 1) {
            countRow--;
            if (matrix[countRow][col] == 'q') {
                return false;
            }
        }

        //надолу
        countRow = row;
        while (countRow <= 6) {
            countRow++;
            if (matrix[countRow][col] == 'q') {
                return false;
            }
        }

        return true;
    }

    private static boolean findHorizonatal(int row, int col, char[][] matrix) {
        //наляво
        int countCol = col;
        while (countCol > 0) {
            countCol--;
            if (matrix[row][countCol] == 'q') {
                return false;
            }
        }

        //надясно
        countCol = col;
        while (col <= 6) {
            col++;
            if (matrix[row][col] == 'q') {
                return false;
            }
        }

        return true;
    }
}
