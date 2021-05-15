package pizzaCallories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        this.setName(name);
        this.setToppingsCount(numberOfToppings);
    }

    private void setToppingsCount(int numberOfToppings) {
        if (numberOfToppings < 0 || numberOfToppings > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = new ArrayList<>(numberOfToppings);
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 1 || name.trim().length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public String getName() {
        return name;
    }

    public Dough getDough() {
        return dough;
    }

    private void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", this.getName(), this.getOverallCalories());
    }

    public double getOverallCalories() {
        return this.dough.calculateCalories() +
                this.toppings.stream()
                .mapToDouble(t -> t.calculateCalories())
                .sum();
    }
}
