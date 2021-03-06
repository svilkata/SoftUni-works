package TestParameterBounds;

public class Main {
    public static class Cat extends Animal {

    }

    public static class Dog extends Animal {

    }

    public static class Car {

    }

    public static void main(String[] args) {
        AnimalList<Animal> animals = new AnimalList<>();
        animals.add(new Cat());
        animals.add(new Dog());
//        animals.add(new Car());

    }
}
