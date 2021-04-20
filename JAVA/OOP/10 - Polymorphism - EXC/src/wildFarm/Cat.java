package wildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String animalType, String animalName, Double animalWeight, String livingRegion, String breed) {
        super(animalType, animalName, animalWeight, livingRegion);
        this.breed = breed;
    }

    @Override
    void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    void eat(Integer toEat, String foodType) {
        super.eat(toEat, foodType);
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %s, %d]", super.animalType, super.animalName, this.breed,
                super.animalWeight, this.livingRegion, super.foodEaten);
    }
}
