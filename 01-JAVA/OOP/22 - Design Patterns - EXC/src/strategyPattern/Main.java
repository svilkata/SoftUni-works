package strategyPattern;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat(new NotHungryEatBehaviour());
        cat1.howToEat();

        Cat cat2 = new Cat(new MessyEatBehaviour());
        cat2.howToEat();

        Cat cat3 = new Cat(new CleanEatBehaviour());
        cat3.howToEat();
    }
}
