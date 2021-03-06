import java.util.Scanner;

public class TheHeiganDance {
    public static boolean[][] matrix = new boolean[15][15];
    public static int[] playerPosition = new int[]{7, 7};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        nextTurnMatrixToZero();

        double playerDamageEachTurnToHeigan = Double.parseDouble(sc.nextLine());
        int playerHealth = 18500;
        double heiganHealth = 3000000.0;
        int countCloudEffect = 0;
        String finalSpellPlayerIsKilled = "";


        while (playerHealth > 0 && heiganHealth > 0) {
            heiganHealth -= playerDamageEachTurnToHeigan;

            if (countCloudEffect == 1) { //from previous turn
                playerHealth -= 3500;
                countCloudEffect = 0;
                if (playerHealth <= 0) {
                    finalSpellPlayerIsKilled = "Plague Cloud";
                }
            }

            if (playerHealth <= 0 && heiganHealth <= 0) {
                playerHealth += 3500;
                break;
            }

            String[] tokens = sc.nextLine().split("\\s+");
            String spell = tokens[0];
            int rowToHit = Integer.parseInt(tokens[1]);
            int colToHit = Integer.parseInt(tokens[2]);


            if (ifHeiganHitsThePlayer(rowToHit, colToHit)) {
                switch (spell) {
                    case "Cloud":
                        playerHealth -= 3500;
                        countCloudEffect = 1;
                        if (playerHealth <= 0) {
                            finalSpellPlayerIsKilled = "Plague Cloud";
                        }
                        break;
                    case "Eruption":
                        playerHealth -= 6000;
                        if (playerHealth <= 0) {
                            finalSpellPlayerIsKilled = "Eruption";
                        }
                        break;
                }
            }
            nextTurnMatrixToZero();
        }

        if (heiganHealth > 0) {
            System.out.println(String.format("Heigan: %.2f", heiganHealth));
            System.out.println(String.format("Player: Killed by %s", finalSpellPlayerIsKilled));
        } else {
            System.out.println("Heigan: Defeated!");
            System.out.println(String.format("Player: %d", playerHealth));
        }

        System.out.println(String.format("Final position: %d, %d", playerPosition[0], playerPosition[1]));


    }

    private static void nextTurnMatrixToZero() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = false;
            }
        }
    }

    private static boolean ifHeiganHitsThePlayer(int rowToHit, int colToHit) {
        for (int i = rowToHit - 1; i <= rowToHit + 1; i++) {
            for (int j = colToHit - 1; j <= colToHit + 1; j++) {
                try {
                    matrix[i][j] = true; //hit cell
                } catch (Exception e) {
//                    System.out.println("Outside boundaries" + i + "-" + j);
                }
            }
        }

        boolean isPlayerHit = false;
        if (matrix[playerPosition[0]][playerPosition[1]] == true) {
            isPlayerHit = true;
        }

        if (playerPosition[0] - 1 >= 0 && matrix[playerPosition[0] - 1][playerPosition[1]] == false) {
            playerPosition[0]--;
            return false; // player not hit
        } else if (playerPosition[1] + 1 <= 14 && matrix[playerPosition[0]][playerPosition[1] + 1] == false) {
            playerPosition[1]++;
            return false; // player not hit
        } else if (playerPosition[0] + 1 <= 14 && matrix[playerPosition[0] + 1][playerPosition[1]] == false) {
            playerPosition[0]++;
            return false; // player not hit
        } else if (playerPosition[1] - 1 >= 0 && matrix[playerPosition[0]][playerPosition[1] - 1] == false) {
            playerPosition[1]--;
            return false; // player not hit
        } else {
            return true; //player can not escape and player is hit
        }

    }


}
