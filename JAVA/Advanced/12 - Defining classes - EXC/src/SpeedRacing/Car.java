package SpeedRacing;

public class Car {
    private String model;
    private double fuelAmount;
    private double fuelCostForKm;
    private int distanceTravelled;

    public Car(String model, double fuelAmount, double fuelCostForKm) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.fuelCostForKm = fuelCostForKm;
        this.distanceTravelled = 0;
    }

    public String getModel() {
        return model;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public double getFuelCostForKm() {
        return fuelCostForKm;
    }

    public boolean canMove(int distance) {
        return this.fuelAmount >= distance * this.fuelCostForKm;
    }
}
