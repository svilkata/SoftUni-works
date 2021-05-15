package needForSPpeed;

public class Car extends Vehicle {
    private final static double DEFAULT_FUEL_CONSUMPTION = 3.00;

    public Car(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(Car.DEFAULT_FUEL_CONSUMPTION);
    }
}
