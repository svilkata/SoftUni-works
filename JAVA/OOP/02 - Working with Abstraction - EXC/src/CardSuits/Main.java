package CardSuits;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        sc.nextLine();
        System.out.println("Card Suits:");

        for (CardSuit value : CardSuit.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", value.ordinal(), value.toString());
        }

    }
}
