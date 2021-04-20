package factoryCreationalPattern.models;

import factoryCreationalPattern.models.intrfcs.Animal;

public class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Miauo");
    }
}
