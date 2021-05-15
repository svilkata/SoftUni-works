package wildFarm;

public abstract class Animal {
    protected String animalType;
    protected String animalName;
    protected Double animalWeight;
    protected Integer foodEaten;

    abstract void makeSound();
    abstract void eat(Integer toEat, String foodType);
}
