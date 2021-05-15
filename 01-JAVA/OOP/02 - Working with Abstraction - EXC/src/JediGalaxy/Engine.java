package JediGalaxy;

import java.util.Arrays;

public class Engine {
    private ConsoleReader reader;
    private String command;
    private Enemy enemy;
    private Player player;

    public Engine(ConsoleReader reader, Enemy enemy, Player player) {
        this.reader = reader;
        this.enemy = enemy;
        this.player = player;
        this.command = "";
    }

    public void run() {
        this.command = this.reader.readLine();
        while (!"Let the Force be with you".equals(command)) {
            int[] playerStartPosition = InputParser.parseIntegerArray(this.command);
            int[] enemyStartPosition = InputParser.parseIntegerArray(reader.readLine());
            int enemyRow = enemyStartPosition[0];
            int enemyCol = enemyStartPosition[1];
            enemy.destroyStars(enemyRow, enemyCol);

            int playerRow = playerStartPosition[0];
            int playerCol = playerStartPosition[1];
            player.collectStars(playerRow, playerCol);

            this.command = reader.readLine();
        }
    }
}
