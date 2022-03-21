import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AddressJsonDto implements Serializable {
    @Expose
    private String country;
    @Expose
    private String city;
    @Expose
    private String street;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}
