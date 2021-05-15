package RawData;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private FourTyres tyres;

    public Car(String model) {
        this.model = model;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setTyres(FourTyres tyres) {
        this.tyres = tyres;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public FourTyres getTyres() {
        return tyres;
    }
}
