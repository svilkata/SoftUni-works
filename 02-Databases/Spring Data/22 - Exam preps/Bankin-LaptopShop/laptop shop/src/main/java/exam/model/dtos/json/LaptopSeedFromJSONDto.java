package exam.model.dtos.json;

import exam.model.entities.enums.WarrantyType;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class LaptopSeedFromJSONDto {
    @Size(min = 9)
    private String macAddress;

    @Positive
    private double cpuSpeed;

    @Min(8)
    @Max(128)
    private int ram;

    @Min(128)
    @Max(1024)
    private int storage;

    @Size(min = 10)
    private String description;

    @Positive
    private BigDecimal price;

    @NotNull
    private WarrantyType warrantyType;  //String value, not ordinal!!!

    private ShopJsonDto shop;

    public LaptopSeedFromJSONDto() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public ShopJsonDto getShop() {
        return shop;
    }

    public void setShop(ShopJsonDto shop) {
        this.shop = shop;
    }
}
