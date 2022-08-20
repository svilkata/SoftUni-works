package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OfferUpdateBindingFlashAttrModelDTO {
    private Long id;

    @NotEmpty
    private String description;

    @NotNull
    private EngineEnum engine;

    @NotEmpty
    private String imageUrl;

    @Positive
    @NotNull
    private Integer price;

    @NotNull
    private TransmissionEnum transmission;

    @Min(1900)
    @NotNull
    private Integer year;

    @NotNull
    @Positive
    private Integer mileage;

    public OfferUpdateBindingFlashAttrModelDTO() {
    }

    public String getDescription() {
        return description;
    }

    public OfferUpdateBindingFlashAttrModelDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferUpdateBindingFlashAttrModelDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateBindingFlashAttrModelDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OfferUpdateBindingFlashAttrModelDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferUpdateBindingFlashAttrModelDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferUpdateBindingFlashAttrModelDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferUpdateBindingFlashAttrModelDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferUpdateBindingFlashAttrModelDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
