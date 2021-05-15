package factoryCreationalPattern.factories;

import factoryCreationalPattern.models.intrfcs.Animal;

public interface Factory {
    Animal createAnimal(String type);
}
