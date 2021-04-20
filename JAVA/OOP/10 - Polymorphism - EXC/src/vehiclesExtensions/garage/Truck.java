package vehiclesExtensions.garage;

public class Truck extends VehicleImpl {
    public static final double AIR_CONDITIONER_EXTRA_CONSUMPTION = 1.6;
    private static final String CLAZZ_NAME = "Truck";

    public Truck(double fuelQuantity, double consumption, double capacity) {
        super(fuelQuantity, consumption + AIR_CONDITIONER_EXTRA_CONSUMPTION, capacity);
    }

    @Override
    public void drive(double distance) {
        System.out.print(CLAZZ_NAME);
        super.drive(distance);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * 0.95);
    }

    @Override
    public String toString() {
        return CLAZZ_NAME + super.toString();
    }
}
