package restaurant;

import java.math.BigDecimal;

public class Dessert extends Food {
    private double calories;
    public Dessert(String name, BigDecimal bigDecimal, double grams, double calories) {
        super(name, bigDecimal, grams);
        this.calories = calories;
    }

    public double getCalories() {
        return this.calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
