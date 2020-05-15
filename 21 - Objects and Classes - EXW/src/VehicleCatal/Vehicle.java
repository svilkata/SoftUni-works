package VehicleCatal;

public class Vehicle {
//    Type: {typeOfVehicle}
//    Brand: {modelOfVehicle}
//    Color: {colorOfVehicle}
//    Horsepower: {horsepowerOfVehicle}
    private String typeOfVehicle;
    private String modelOfVehicle;
    private String colourOfVehicle;
    private int horsepowerOfVehicle;

    public Vehicle(String typeOfVehicle, String modelOfVehicle, String colourOfVehicle, int horsepowerOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
        this.modelOfVehicle = modelOfVehicle;
        this.colourOfVehicle = colourOfVehicle;
        this.horsepowerOfVehicle = horsepowerOfVehicle;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public String getModelOfVehicle() {
        return modelOfVehicle;
    }

    public String getColourOfVehicle() {
        return colourOfVehicle;
    }

    public int getHorsepowerOfVehicle() {
        return horsepowerOfVehicle;
    }

    @Override
    public String toString() {
        String result = String.format("Type: %s%nModel: %s%nColor: %s%nHorsepower: %s",
                this.typeOfVehicle, this.modelOfVehicle, this.colourOfVehicle, this.horsepowerOfVehicle);
        return result;
    }
}
