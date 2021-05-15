package facade;

public class CarBuilderFacade {
    private Car car;

    CarBuilderFacade () {
        this.car = new Car();
    }

    public CarInfoBuilder info() {
        return new CarInfoBuilder();
    }
}
