package vehicles;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double consumption;
    private static final DecimalFormat formatter = new DecimalFormat("#.##");

    protected VehicleImpl(double fuelQuantity, double consumption) {
        this.fuelQuantity = fuelQuantity;
        this.consumption = consumption;
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

    private boolean canDrive(double distance) {
        boolean canDrive = this.fuelQuantity >= this.consumption * distance;
        return canDrive;
    }

    @Override
    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format(": %.2f", this.fuelQuantity);
    }
}
