package CarSalesman;

public class Car {
    private String modelCar;
    private Engine engineCar;
    private String weightCar;
    private String colourCar;
//    model, engine, weight and color


    public Car() {
    }

    public Car(String modelCar, Engine engineCar) {
        this(modelCar, engineCar, "n/a", "n/a");
    }

    public Car CarWithWeight(String modelCar, Engine engineCar, String weightCar) {
        return new Car(modelCar, engineCar, weightCar, "n/a");
    }

    public Car CarWithColour(String modelCar, Engine engineCar, String colourCar) {
        return new Car(modelCar, engineCar, "n/a", colourCar);
    }

    public Car(String modelCar, Engine engineCar, String weightCar, String colourCar) {
        this.modelCar = modelCar;
        this.engineCar = engineCar;
        this.weightCar = weightCar;
        this.colourCar = colourCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public Engine getEngineCar() {
        return engineCar;
    }

    public String getWeightCar() {
        return weightCar;
    }

    public String getColourCar() {
        return colourCar;
    }
}
