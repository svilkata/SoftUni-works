package restaurant;

import java.math.BigDecimal;

public abstract class Beverage extends Product {
    private double milliliters;

    public Beverage(String name, BigDecimal bigDecimal, double milliliters) {
        super(name, bigDecimal);
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return this.milliliters;
    }

    public void setMilliliters(double milliliters) {
        this.milliliters = milliliters;
    }
}
