package RawData;

public class FourTyres {
    private String modelCar;
    private double tyrePressure1;
    private int tyreAge1;
    private double tyrePressure2;
    private int tyreAge2;
    private double tyrePressure3;
    private int tyreAge3;
    private double tyrePressure4;
    private int tyreAge4;

    public FourTyres(String modelCar, double tyrePressure1, int tyreAge1, double tyrePressure2, int tyreAge2, double tyrePressure3, int tyreAge3, double tyrePressure4, int tyreAge4) {
        this.modelCar = modelCar;
        this.tyrePressure1 = tyrePressure1;
        this.tyreAge1 = tyreAge1;
        this.tyrePressure2 = tyrePressure2;
        this.tyreAge2 = tyreAge2;
        this.tyrePressure3 = tyrePressure3;
        this.tyreAge3 = tyreAge3;
        this.tyrePressure4 = tyrePressure4;
        this.tyreAge4 = tyreAge4;
    }

    public String getModelCar() {
        return modelCar;
    }

    public double getTyrePressure1() {
        return tyrePressure1;
    }

    public int getTyreAge1() {
        return tyreAge1;
    }

    public double getTyrePressure2() {
        return tyrePressure2;
    }

    public int getTyreAge2() {
        return tyreAge2;
    }

    public double getTyrePressure3() {
        return tyrePressure3;
    }

    public int getTyreAge3() {
        return tyreAge3;
    }

    public double getTyrePressure4() {
        return tyrePressure4;
    }

    public int getTyreAge4() {
        return tyreAge4;
    }

    public boolean isATyreLessPressureThan1() {
        if (this.tyrePressure1 < 1.0 || this.tyrePressure2 < 1.0
        || this.tyrePressure3 < 1.0 || this.tyrePressure4 < 1.0) {
            return true;
        } else {
            return false;
        }
    }
}
