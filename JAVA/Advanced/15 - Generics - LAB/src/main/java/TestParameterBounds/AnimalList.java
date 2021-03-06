package TestParameterBounds;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;


public class AnimalList<T extends Animal> {
    private List<T> animals = new ArrayList<>();

    public void add(T animal){
        this.animals.add(animal);
    }

    public void sleepAllAnimals(){
        for (T animal : animals) {
            animal.sleep();
        }
    }
}
