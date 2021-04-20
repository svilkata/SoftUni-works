package strategyPattern;

public class CleanEatBehaviour implements EatBehaviour {
    @Override
    public void eat() {
        System.out.println("I ate some and everything is clean!");
    }
}
