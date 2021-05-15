package CarSalesman;

public class Car {
    private String modelCar;
    private String weightCar; // in kg
    private String color;
    private Engine connectWithEnginClassModelEngine;

    public Car(String modelCar) {
        this.modelCar = modelCar;
        this.weightCar = "n/a";
        this.color = "n/a";
    }

    public void setWeightCar(String weightCar) {
        this.weightCar = weightCar;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setConnectWithEnginClassModelEngine(Engine connectWithEnginClassModelEngine) {
        this.connectWithEnginClassModelEngine = connectWithEnginClassModelEngine;
    }

    public Engine getConnectWithEnginClassModelEngine() {
        return connectWithEnginClassModelEngine;
    }

    @Override
    public String toString() {
        String result = String.format("Weight: %s%nColor: %s", this.weightCar, this.color);
        return result;
    }
}
