package factoryCreationalPattern.models;

import factoryCreationalPattern.models.intrfcs.Animal;

public class Giraffe implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Muuuu aaa");
    }
}
