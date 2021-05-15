package facade;

public class CarInfoBuilder {
    private String type;
    private String color;
    private int numberOfDoors;

    public CarInfoBuilder() {
    }

    public CarInfoBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CarInfoBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public CarInfoBuilder withNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
        return this;
    }
}
