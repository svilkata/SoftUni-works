package factoryCreationalPattern;

import factoryCreationalPattern.models.intrfcs.Animal;

import java.util.List;

public interface Forest {
    void addAnimal(Animal animal);
    public List<Animal> getAnimals();
}
