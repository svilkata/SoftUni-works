package wildFarm;

public class Tiger extends Felime {
    public Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    void makeSound() {
        System.out.println("ROAAR!!!");
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
