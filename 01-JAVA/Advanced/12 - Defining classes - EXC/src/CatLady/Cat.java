package CatLady;

import java.util.ArrayList;
import java.util.List;

public class Cat {
    private String name;
    private String type;
    private double characteristics;


    public Cat(String name, String type, double characteristics) {
        this.name = name;
        this.type = type;
        this.characteristics = characteristics;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getCharacteristics() {
        return characteristics;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCharacteristics(int characteristics) {
        this.characteristics = characteristics;
    }
}
