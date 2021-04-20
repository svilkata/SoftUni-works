package JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();
        int[] dimentions = InputParser.parseIntegerArray(reader.readLine());
        int rows = dimentions[0];
        int cols = dimentions[1];
        GalaxyMatrix matrixGalaxy = new GalaxyMatrix(new Field(new int[rows][cols]));

        Enemy enemy = new Enemy(matrixGalaxy);
        Player player = new Player(matrixGalaxy);
        Engine engine = new Engine(reader, enemy, player);
        engine.run();

        System.out.println(player.getPoints());
    }
}
