package pizzaCallories;

import enums.ToppingType;
import utils.Validator;

public class Topping {
    private double weight;
    private String toppingType;

    protected Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        Validator.validateToppingWeight(weight, toppingType);
        this.setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private void setToppingType(String toppingType) {
        try {
            ToppingType.valueOf(toppingType.toUpperCase());
            this.toppingType = toppingType;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }
    }

    public String getToppingType() {
        return this.toppingType;
    }

    public double calculateCalories() {
        double calories = this.weight * 2;
        calories *= ToppingType.valueOf(this.toppingType.toUpperCase()).getModifier();

        return calories;
    }
}
