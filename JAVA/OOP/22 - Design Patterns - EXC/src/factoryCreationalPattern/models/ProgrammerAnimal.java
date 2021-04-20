package factoryCreationalPattern.models;

import factoryCreationalPattern.models.intrfcs.Animal;

public class ProgrammerAnimal implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Hello World");
    }
}
