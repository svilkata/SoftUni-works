package vehicles;

import javax.print.DocFlavor;

public class Truck extends VehicleImpl {
    public static final double AIR_CONDITIONER_EXTRA_CONSUMPTION = 1.6;
    private static final String CLAZZ_NAME = "Truck";

    protected Truck(double fuelQuantity, double consumption) {
        super(fuelQuantity, consumption + AIR_CONDITIONER_EXTRA_CONSUMPTION);
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
