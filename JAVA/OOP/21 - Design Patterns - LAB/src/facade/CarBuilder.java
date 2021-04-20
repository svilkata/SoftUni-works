package facade;

public class CarBuilder {
    private String type;
    private String color;
    private int numberOfDoors;
    private String city;
    private String address;

    public CarBuilder() {
    }

    public CarBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CarBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public CarBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public CarBuilder withAddress(String address) {
        this.city = city;
        return this;
    }

    public CarBuilder withNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
        return this;
    }

    public Car build() {
        Car car = new Car();
        if (this.type != null) {
            car.setType(this.type);
        }

        if (this.color != null) {
            car.setColor(this.color);
        }

        if (this.numberOfDoors != 0) {
            car.setNumberOfDoors(this.numberOfDoors);
        }

        if (this.city != null) {
            car.setCity(this.city);
        }

        if (this.address != null) {
            car.setAddress(this.address);
        }

        return car;
    }
}
