package restaurant;

import java.math.BigDecimal;

public class Coffee extends HotBeverage {
    private static final double COFFEE_MILLILITERS = 50;
    private static final BigDecimal COFFEE_PRICE = BigDecimal.valueOf(3.50);
    private double caffeine;


    public Coffee(String name, double caffeine) {
        super(name, Coffee.COFFEE_PRICE, Coffee.COFFEE_MILLILITERS);
        this.caffeine = caffeine;
    }

    public double getCaffeine() {
        return caffeine;
    }

    public static double getCoffeeMilliliters() {
        return COFFEE_MILLILITERS;
    }

    public static BigDecimal getCoffeePrice() {
        return COFFEE_PRICE;
    }

    public void setCaffeine(double caffeine) {
        this.caffeine = caffeine;
    }
}
