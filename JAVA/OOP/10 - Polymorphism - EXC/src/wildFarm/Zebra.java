package wildFarm;

public class Zebra extends Mammal {
    public Zebra(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
    }

    @Override
    void makeSound() {
        System.out.println("Zs");
    }

    @Override
    void eat(Integer toEat, String foodType) {
        super.eat(toEat, foodType);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
