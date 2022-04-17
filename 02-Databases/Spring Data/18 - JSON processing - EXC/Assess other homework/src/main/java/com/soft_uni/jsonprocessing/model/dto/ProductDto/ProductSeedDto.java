package com.soft_uni.jsonprocessing.model.dto.ProductDto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductSeedDto {
    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public ProductSeedDto() {
    }

    @Size(min = 3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
