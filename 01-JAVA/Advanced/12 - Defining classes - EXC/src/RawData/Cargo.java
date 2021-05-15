package RawData;

public class Cargo {
    private String modelCar;
    private int weightCargo;
    private String typeCargo;

    public Cargo(String model, int weightCargo, String typeCargo) {
        this.modelCar = model;
        this.weightCargo = weightCargo;
        this.typeCargo = typeCargo;
    }

    public String getModelCar() {
        return modelCar;
    }

    public int getWeightCargo() {
        return weightCargo;
    }

    public String getTypeCargo() {
        return typeCargo;
    }

    public void setWeightCargo(int weightCargo) {
        this.weightCargo = weightCargo;
    }

    public void setTypeCargo(String typeCargo) {
        this.typeCargo = typeCargo;
    }
}
