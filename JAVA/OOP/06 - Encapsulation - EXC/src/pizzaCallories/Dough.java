package pizzaCallories;

import enums.BakingTechniques;
import enums.FlourType;
import utils.Validator;

public class Dough {
    private double weight;
    private String flourType;
    private String bakingTechnique;

    public Dough(String flour, String bakingTechnique, double weight) {
        Validator.validateDoughWeight(weight);
        this.setFlourType(flour);
        this.bakingTechnique = bakingTechnique;
        this.setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private void setFlourType(String flour) {
        try {
            FlourType.valueOf(flour.toUpperCase());
            this.flourType = flour;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    public String getFlourType() {
        return this.flourType;
    }

    public String getBakingTechnique() {
        return this.bakingTechnique;
    }

    public void setBakingTechnique(String bakingTechnique) {
        this.bakingTechnique = bakingTechnique;
    }

    public double calculateCalories() {
        double calories = this.weight * 2;
        calories *= FlourType.valueOf(this.flourType.toUpperCase()).getModifier();
        calories *= BakingTechniques.valueOf(this.bakingTechnique.toUpperCase()).getModifier();

        return calories;
    }
}
