package restaurant;

import java.math.BigDecimal;

public abstract class Food extends Product {
    private double grams;
    public Food(String name, BigDecimal bigDecimal, double grams) {
        super(name, bigDecimal);
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }
}
