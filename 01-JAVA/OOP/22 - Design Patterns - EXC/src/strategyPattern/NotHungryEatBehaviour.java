package strategyPattern;

public class NotHungryEatBehaviour implements EatBehaviour {
    @Override
    public void eat() {
        System.out.println("I am not hungry - I am not a real cat!");
    }
}
