package wildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    protected String livingRegion;

    public Mammal(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super.animalType = animalType;
        super.animalName = animalName;
        super.animalWeight = animalWeight;
        super.foodEaten = 0;
        this.livingRegion = livingRegion;
    }

    public void setFoodStatus(Integer foodToEat) {
        super.foodEaten = foodToEat;
    }

    public Integer getFoodStatus() {
        return super.foodEaten;
    }

    @Override
    void eat(Integer toEat, String foodType) {
        switch (super.animalType) {
            case "Mouse":
                if (foodType.equals("Vegetable")) {
                    this.setFoodStatus(this.getFoodStatus() + toEat);
                } else {
                    System.out.printf("Mice are not eating that type of food!%n");
                }
                break;
            case "Zebra":
                if (foodType.equals("Vegetable")) {
                    this.setFoodStatus(this.getFoodStatus() + toEat);
                } else {
                    System.out.printf("%ss are not eating that type of food!%n", super.animalType);
                }
                break;
            case "Tiger":
                if (foodType.equals("Meat")) {
                    this.setFoodStatus(this.getFoodStatus() + toEat);
                } else {
                    System.out.printf("%ss are not eating that type of food!%n", super.animalType);
                }
                break;
            case "Cat":
                this.setFoodStatus(this.getFoodStatus() + toEat);
                break; //case Cat
        }
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %d]", super.animalType, super.animalName,
                super.animalWeight, this.livingRegion, super.foodEaten);
    }
}
