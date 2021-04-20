package facade;

public class CarAddressBuilder {
    private String city;
    private String address;

    public CarAddressBuilder() {
    }

    public CarAddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public CarAddressBuilder withCAddress(String address) {
        this.address = address;
        return this;
    }

}
