package vehiclesExtensions.garage;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private static final DecimalFormat formatter = new DecimalFormat("#.##");

    private double fuelQuantity;
    private double consumption;
    private double tankCapacity;

    protected VehicleImpl(double fuelQuantity, double consumption, double tankCapacity) {
        this.setFuelQuantity(fuelQuantity);
        this.setConsumption(consumption);
        this.setTankCapacity(tankCapacity);
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.validateFuel(fuelQuantity);
        this.fuelQuantity = fuelQuantity;
    }

    public void setConsumption(double consumption) {
        this.validateFuel(consumption);
        this.consumption = consumption;
    }

    public void setTankCapacity(double tankCapacity) {
        this.validateFuel(tankCapacity);
        this.tankCapacity = tankCapacity;
    }

    @Override
    public void drive(double distance) {
        if (canDrive(distance)) {
            this.fuelQuantity -= this.consumption * distance;
            System.out.println(String.format(" travelled %s km", formatter.format(distance)));
        } else {
            System.out.println(" needs refueling");
        }
    }

    @Override
    public void driveWithPassengers(double distance) {
        this.drive(distance);
    }

    protected void addConsumptionBus(double additional) {
        this.consumption += additional;
    }

    protected void subtractConsumptionBus(double additional) {
        this.consumption -= additional;
    }

    private boolean canDrive(double distance) {
        return this.fuelQuantity >= this.consumption * distance;
    }

    private void validateFuel(double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
    }

    @Override
    public void refuel(double liters) {
        this.validateFuel(liters);
        if (this.fuelQuantity + liters <= this.tankCapacity) {
            this.fuelQuantity += liters;
        } else {
            System.out.println("Cannot fit fuel in tank");
        }
    }

    @Override
    public String toString() {
        return String.format(": %.2f", this.fuelQuantity);
    }
}
