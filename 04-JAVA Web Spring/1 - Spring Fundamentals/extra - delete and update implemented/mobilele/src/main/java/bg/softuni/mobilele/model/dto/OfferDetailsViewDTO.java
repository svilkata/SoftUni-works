package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

public class OfferDetailsViewDTO {
    private Long id;
    private Integer mileage;
    private Integer price;
    private EngineEnum engine;
    private TransmissionEnum transmission;
    private Long modelId;
    private String imageUrl;
    private Integer year;
    private String modelName;
    private String description;
    private String modelBrandName;
    private String sellerFirstName;
    private String sellerLastName;

    public OfferDetailsViewDTO() {
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public OfferDetailsViewDTO setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
        return this;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public OfferDetailsViewDTO setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferDetailsViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getModelBrandName() {
        return modelBrandName;
    }

    public OfferDetailsViewDTO setModelBrandName(String modelBrandName) {
        this.modelBrandName = modelBrandName;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferDetailsViewDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OfferDetailsViewDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferDetailsViewDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferDetailsViewDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public OfferDetailsViewDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailsViewDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferDetailsViewDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public OfferDetailsViewDTO setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDetailsViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
