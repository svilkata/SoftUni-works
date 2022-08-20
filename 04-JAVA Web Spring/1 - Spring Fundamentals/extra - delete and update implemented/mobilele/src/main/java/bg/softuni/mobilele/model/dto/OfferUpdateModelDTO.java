package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

public class OfferUpdateModelDTO {
    private Long id;
    private String description;
    private EngineEnum engine;
    private String imageUrl;
    private Integer price;
    private TransmissionEnum transmission;
    private Integer year;
    private Integer mileage;

    public OfferUpdateModelDTO() {
    }

    public String getDescription() {
        return description;
    }

    public OfferUpdateModelDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferUpdateModelDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateModelDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OfferUpdateModelDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferUpdateModelDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferUpdateModelDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferUpdateModelDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferUpdateModelDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
