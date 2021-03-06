package RawData;

public class Engine {
    private String modelCar;
    private int speedEngine;
    private int powerEngine;

    public Engine(String model, int speedEngine, int powerEngine) {
        this.modelCar = model;
        this.speedEngine = speedEngine;
        this.powerEngine = powerEngine;
    }

    public String getModelCar() {
        return modelCar;
    }

    public int getSpeedEngine() {
        return speedEngine;
    }

    public int getSpeedPower() {
        return this.powerEngine;
    }

    public void setSpeedEngine(int speedEngine) {
        this.speedEngine = speedEngine;
    }

    public void setSpeedPower(int powerEngine) {
        this.powerEngine = powerEngine;
    }

    public boolean isMoreThan250HP() {
        if (this.powerEngine > 250) {
            return true;
        } else {
            return false;
        }
    }
}
