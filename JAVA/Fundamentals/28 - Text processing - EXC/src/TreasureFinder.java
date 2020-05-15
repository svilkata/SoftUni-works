import java.util.Arrays;
import java.util.Scanner;

public class TreasureFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] key = sc.nextLine().split("\\s+");

        String input = sc.nextLine();
        while (!"find".equals(input)) {
            String decryptedMessage = decryptMessage(input, key);
            int startIndexTypeTreasure = decryptedMessage.indexOf('&');
            int endIndexTypeTreasure = decryptedMessage.lastIndexOf('&');
            String typeTreasure = decryptedMessage.substring(startIndexTypeTreasure + 1, endIndexTypeTreasure);

            int startIndexCoordinates = decryptedMessage.indexOf('<');
            int endIndexCoordinates = decryptedMessage.lastIndexOf('>');
            String coordinates = decryptedMessage.substring(startIndexCoordinates + 1, endIndexCoordinates);
            System.out.println(String.format("Found %s at %s", typeTreasure, coordinates));

            input = sc.nextLine();
        }


        System.out.println();
    }

    private static String decryptMessage(String input, String[] key) {
        StringBuilder newMessage = new StringBuilder();
        int j = 0;
        for (int i = 0; i < input.length(); i++) {
            if (j == key.length) {
                j = 0;
            }
            char symbolToAppend = (char) ((int) input.charAt(i) - Integer.parseInt(key[j]));
            newMessage.append(symbolToAppend);
            j++;
        }
        return newMessage.toString();
    }
}
