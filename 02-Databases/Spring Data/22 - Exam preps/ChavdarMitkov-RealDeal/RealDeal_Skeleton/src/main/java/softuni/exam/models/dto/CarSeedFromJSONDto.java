package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.adapters.LocalDateAdapter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class CarSeedFromJSONDto {
    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private Integer kilometers;

    @Expose
    private String registeredOn;

//    @Expose
//    @XmlJavaTypeAdapter(LocalDateAdapter.class)
//    private LocalDate registeredOn;

    public CarSeedFromJSONDto() {
    }

    @Size(min = 2, max = 19)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Size(min = 2, max = 19)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Positive
    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }
}
