package factoryCreationalPattern.factories;

import factoryCreationalPattern.Forest;
import factoryCreationalPattern.models.Cat;
import factoryCreationalPattern.models.Giraffe;
import factoryCreationalPattern.models.Monkey;
import factoryCreationalPattern.models.ProgrammerAnimal;
import factoryCreationalPattern.models.intrfcs.Animal;

public class AnimalFactory implements Factory {
    private Forest forest;

    public AnimalFactory(Forest forest) {
        this.forest = forest;
    }

    @Override
    public Animal createAnimal(String type) {
        Animal animal;

        switch (type) {
            case "Monkey":
                animal = new Monkey();
                break;
            case "ProgrammerAnimal":
                animal = new ProgrammerAnimal();
                break;
            case "Giraffe":
                animal = new Giraffe();
                break;
            case "Cat":
                animal = new Cat();
                break;
            default:
                animal = null;
                break;
        }

        if (animal != null) {
            this.forest.addAnimal(animal);
        }

        return animal;
    }
}
