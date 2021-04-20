package CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String power = sc.nextLine();
        String suit = sc.nextLine();

        CardPower powerEnum = CardPower.valueOf(power);
        CardSuit suitEnum = CardSuit.valueOf(suit);

        Card card = new Card(powerEnum, suitEnum);

        System.out.println(card.toString());

    }
}
