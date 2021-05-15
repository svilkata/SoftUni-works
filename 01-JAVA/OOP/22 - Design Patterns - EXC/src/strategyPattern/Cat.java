package strategyPattern;

public class Cat {
    private EatBehaviour eatBehaviour;

    public Cat(EatBehaviour eatBehaviour) {
        this.eatBehaviour = eatBehaviour;
    }

    public void howToEat() {
        this.eatBehaviour.eat();
    }
}
