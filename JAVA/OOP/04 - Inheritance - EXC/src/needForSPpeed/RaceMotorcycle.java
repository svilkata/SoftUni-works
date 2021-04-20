package needForSPpeed;

public class RaceMotorcycle extends Motorcycle {
    private final static double DEFAULT_FUEL_CONSUMPTION = 8.00;

    public RaceMotorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(RaceMotorcycle.DEFAULT_FUEL_CONSUMPTION);
    }
}
