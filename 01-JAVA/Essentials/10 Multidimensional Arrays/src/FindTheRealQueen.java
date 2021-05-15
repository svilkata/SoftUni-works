import java.util.Scanner;

public class FindTheRealQueen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = 8;
        char[][] board = new char[size][size];

        for (int i = 0; i < size; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                board[i][j] = line[j].charAt(0);
            }
        }

//        boolean isMainDiagonalValidDownRight = checkQueenMainDiagonalDownRight(board, 2, 2);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == 'q') {
                    boolean isColValid = checkQueenCol(board, c);
                    boolean isRowValid = checkQueenRow(board, r);
                    boolean isMainDiagonalValidUpLeft = checkQueenMainDiagonalUpLeft(board, r, c);
                    boolean isMainDiagonalValidDownRight = checkQueenMainDiagonalDownRight(board, r, c);
                    boolean isSecondaryDiagonalValidUpRight = checkQueenSecondaryDiagonalUpRight(board, r, c);
                    boolean isSecondaryDiagonalValidDownLeft = checkQueenSecondaryDiagonalDownLeft(board, r, c);

                    if (isColValid && isRowValid && isMainDiagonalValidDownRight && isMainDiagonalValidUpLeft
                            && isSecondaryDiagonalValidDownLeft && isSecondaryDiagonalValidUpRight) {
                        System.out.println(r + " " + c);
                        return;
                    }
                }
            }
        }

        System.out.println();
    }

    private static boolean checkQueenSecondaryDiagonalDownLeft(char[][] board, int row, int col) {
        row++;
        col--;
        while (row < 8 && col >= 0) {
            if (board[row++][col--] == 'q') {
                return false;
            }
        }

        return true;

    }

    private static boolean checkQueenSecondaryDiagonalUpRight(char[][] board, int row, int col) {
        row--;
        col++;
        while (row >= 0 && col < 8) {
            if (board[row--][col++] == 'q') {
                return false;
            }
        }

        return true;
    }

    private static boolean checkQueenMainDiagonalDownRight(char[][] board, int row, int col) {
        row++;
        col++;
        while (row < 8 && col < 8) {
            if (board[row++][col++] == 'q') {
                return false;
            }
        }

        return true;

    }

    private static boolean checkQueenMainDiagonalUpLeft(char[][] board, int row, int col) {
        row--;
        col--;
        while (row >= 0 && col >= 0) {
            if (board[row--][col--] == 'q') {
                return false;
            }
        }

        return true;

    }

    private static boolean checkQueenRow(char[][] board, int row) {
        int queens = 0;

        for (int i = 0; i < board.length; i++) {
            char symbol = board[row][i];
            if (symbol == 'q') {
                queens++;
            }
        }

        return queens == 1;
    }

    private static boolean checkQueenCol(char[][] board, int col) {
        int queens = 0;

        for (int i = 0; i < board.length; i++) {
            char symbol = board[i][col];
            if (symbol == 'q') {
                queens++;
            }
        }

        return queens == 1;
    }
}
