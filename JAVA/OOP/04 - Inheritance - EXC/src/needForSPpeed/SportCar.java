package needForSPpeed;

public class SportCar extends Car {
    private final static double DEFAULT_FUEL_CONSUMPTION = 10.00;

    public SportCar(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(SportCar.DEFAULT_FUEL_CONSUMPTION);
    }


}
