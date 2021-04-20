package vehicles;

public class Car extends VehicleImpl {
    public static final double AIR_CONDITIONER_EXTRA_CONSUMPTION = 0.9;
    private static final String CLAZZ_NAME = "Car";

    protected Car(double fuelQuantity, double consumption) {
        super(fuelQuantity, consumption + AIR_CONDITIONER_EXTRA_CONSUMPTION);
    }

    @Override
    public void drive(double distance) {
        System.out.print(CLAZZ_NAME);
        super.drive(distance);
    }

    @Override
    public String toString() {
        return CLAZZ_NAME + super.toString();
    }
}
