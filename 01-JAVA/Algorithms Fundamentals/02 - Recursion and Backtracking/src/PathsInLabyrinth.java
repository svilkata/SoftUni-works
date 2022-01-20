import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathsInLabyrinth {
    static List<Character> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = Integer.parseInt(sc.nextLine());
        int cols = Integer.parseInt(sc.nextLine());

        char[][] labyrinth = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            labyrinth[row] = sc.nextLine().toCharArray();
        }

        findPath(labyrinth, 0, 0, ' ');
    }

    private static void findPath(char[][] labyrinth, int row, int col, char direction) {
        if (!isInBounds(labyrinth, row, col)
                || labyrinth[row][col] == 'V'
                || labyrinth[row][col] == '*') {
            return;
        }

        path.add(direction);

        if (labyrinth[row][col] == 'e') {
            printPath();
            path.remove(path.size() - 1); //когато backtrack-ваме и без да сме намерили изхода
            return;
        }

        labyrinth[row][col] = 'V'; //visited
        findPath(labyrinth, row - 1, col, 'U'); //up
        findPath(labyrinth, row + 1, col, 'D'); // down
        findPath(labyrinth, row, col - 1, 'L'); // left
        findPath(labyrinth, row, col + 1, 'R'); // right

        labyrinth[row][col] = '-'; //backtracking - след като е посетена, я маркираме като непосетена

        path.remove(path.size() - 1); //когато backtrack-ваме и без да сме намерили изхода
    }

    private static void printPath() {
//        path.remove(0);
        for (int i = 1; i < path.size(); i++) {
            Character character = path.get(i);
            System.out.print(character);
        }
        System.out.println();
    }

    private static boolean isInBounds(char[][] labyrinth, int row, int col) {
        return row < labyrinth.length && row >= 0 && col < labyrinth[row].length && col >= 0;
    }
}
