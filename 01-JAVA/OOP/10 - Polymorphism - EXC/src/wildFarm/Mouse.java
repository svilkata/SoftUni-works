package wildFarm;

import java.text.DecimalFormat;

public class Mouse extends Mammal {
    public Mouse(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
    }

    @Override
    void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eat(Integer toEat, String foodType) {
        super.eat(toEat, foodType);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
