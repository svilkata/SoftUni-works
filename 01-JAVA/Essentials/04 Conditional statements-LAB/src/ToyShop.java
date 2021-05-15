import java.util.Scanner;

public class ToyShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Double excursionPrice = Double.parseDouble(sc.nextLine());
        int puzzleCount = Integer.parseInt(sc.nextLine());
        int dummyCount = Integer.parseInt(sc.nextLine());
        int bearCount = Integer.parseInt(sc.nextLine());
        int minionCount = Integer.parseInt(sc.nextLine());
        int truckCount = Integer.parseInt(sc.nextLine());

        int totalToyCount = puzzleCount + dummyCount + bearCount + minionCount + truckCount;

        double sales = puzzleCount * 2.60 + dummyCount * 3.00 + bearCount * 4.10 + minionCount * 8.20 + truckCount * 2.00;

        if (totalToyCount >= 50) {
            sales *= 0.75;
        }

        double salesAfterRent = sales * 0.90;

        if (salesAfterRent >= excursionPrice) {
            System.out.println(String.format("Yes! %.2f lv left.", salesAfterRent - excursionPrice));
        } else {
            System.out.println(String.format("Not enough money! %.2f lv needed.", excursionPrice - salesAfterRent));
        }
    }
}
