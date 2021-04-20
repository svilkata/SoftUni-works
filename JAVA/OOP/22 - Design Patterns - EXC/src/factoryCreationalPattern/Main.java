package factoryCreationalPattern;

import factoryCreationalPattern.factories.AnimalFactory;
import factoryCreationalPattern.factories.Factory;
import factoryCreationalPattern.models.intrfcs.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Forest forest = new OakForest();
        Factory factory = new AnimalFactory(forest);

        String type = sc.nextLine();
        while (!type.equals("End")) {
            factory.createAnimal(type);
            type = sc.nextLine();
        }

        for (Animal animal : forest.getAnimals()) {
            animal.makeSound();
        }

    }
}
