package vehiclesExtensions.garage;

public class Bus extends VehicleImpl implements Vehicle {
    private static final double AIR_CONDITIONER_BUS_EXTRA_CONSUMPTION = 1.4;
    private static final String CLAZZ_NAME = "Bus";


    public Bus(double fuelQuantity, double consumption, double capacity) {
        super(fuelQuantity, consumption, capacity);
    }

    @Override
    public void drive(double distance) {
        System.out.print(CLAZZ_NAME);
        super.drive(distance);
    }

    @Override
    public void driveWithPassengers(double distance) {
        super.addConsumptionBus(AIR_CONDITIONER_BUS_EXTRA_CONSUMPTION);
        this.drive(distance);
        super.subtractConsumptionBus(AIR_CONDITIONER_BUS_EXTRA_CONSUMPTION);
    }

    @Override
    public String toString() {
        return CLAZZ_NAME + super.toString();
    }
}
