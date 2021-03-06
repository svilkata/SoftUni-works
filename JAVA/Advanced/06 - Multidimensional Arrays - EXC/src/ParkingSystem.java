import java.util.Scanner;

public class ParkingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] dimensions = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        int[][] parking = new int[rows][cols];

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("stop")) break;
            String[] command = input.split(" ");

            int z = Integer.parseInt(command[0]);
            int x = Integer.parseInt(command[1]);
            int y = Integer.parseInt(command[2]);

            int moves = 0;
            boolean found = false;

            if (parking[x][y] == 0) {
                moves = findMoves(z, x, y);
                parking[x][y] = 1;
                found = true;
            } else {
                int range = 1;
                while (!found) {
                    if (y - range >= 1 && parking[x][y - range] == 0) {
                        moves = findMoves(z, x, y - range);
                        found = true;
                        parking[x][y - range] = 1;
                    } else if (y + range < parking[x].length && parking[x][y + range] == 0) {
                        moves = findMoves(z, x, y + range);
                        found = true;
                        parking[x][y + range] = 1;
                    }

                    if (y - range < 1 && y + range >= parking[x].length) {
                        System.out.printf("Row %d full%n", x);
                        break;
                    }

                    range++;
                }
            }

            if (found) System.out.println(moves);
        }
    }

    private static int findMoves(int z, int x, int y) {
        return Math.abs(z - x) + y + 1;
    }
}