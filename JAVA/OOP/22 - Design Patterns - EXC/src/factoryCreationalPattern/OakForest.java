package factoryCreationalPattern;

import factoryCreationalPattern.models.intrfcs.Animal;

import java.util.ArrayList;
import java.util.List;

public class OakForest implements Forest {
    private List<Animal> animals;

    public OakForest() {
        this.animals = new ArrayList<>();
    }

    @Override
    public List<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }
}
