package HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double pricePerDay = Double.parseDouble(sc.next());
        int numberOfDays = sc.nextInt();
        String seasonName = sc.next();
        String discountType = sc.next();

        Season season = Season.valueOf(seasonName);
        Discount discount = Discount.valueOf(discountType);

        System.out.printf("%.2f", getPriceFor(pricePerDay, numberOfDays, season, discount));
    }

    private static double getPriceFor(double pricePerDay, int numberOfDays, Season season, Discount discount) {
        return pricePerDay * season.getSeasonIndex() * numberOfDays * discount.getDiscount();
    }
}
