import java.util.Scanner;

public class CleverLily {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nAge = Integer.parseInt(sc.nextLine());
        double priceWashingMachine = Double.parseDouble(sc.nextLine());
        double singlePriceToy = Double.parseDouble(sc.nextLine());

        double sumMoney = 0.0;
        int toys = 0;

        int count = 0;

        for (int i = 1; i <= nAge; i++) {
            if (i % 2 == 0) {
                count++;
                sumMoney += 10 * count;
            } else {
                toys++;
            }
        }
        sumMoney = sumMoney + toys * singlePriceToy - 1.0 * (nAge / 2);

        if (sumMoney >= priceWashingMachine) {
            System.out.println(String.format("Yes! %.2f", sumMoney - priceWashingMachine));
        } else {
            System.out.println(String.format("No! %.2f", priceWashingMachine - sumMoney));
        }


    }
}
