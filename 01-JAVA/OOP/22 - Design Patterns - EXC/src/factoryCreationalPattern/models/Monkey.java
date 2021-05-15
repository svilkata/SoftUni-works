package factoryCreationalPattern.models;

import factoryCreationalPattern.models.intrfcs.Animal;

public class Monkey implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Uaaaa-haaaa");
    }
}
