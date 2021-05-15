package strategyPattern;

public class MessyEatBehaviour implements EatBehaviour{
    @Override
    public void eat() {
        System.out.println("I ate a lot and I made a mess!");
    }
}
