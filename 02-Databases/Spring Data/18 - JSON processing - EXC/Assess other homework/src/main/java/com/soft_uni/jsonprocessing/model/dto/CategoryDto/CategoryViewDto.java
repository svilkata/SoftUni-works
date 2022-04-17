package com.soft_uni.jsonprocessing.model.dto.CategoryDto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CategoryViewDto {
    @Expose
    private String category;
    @Expose
    private BigInteger productsCount;
    @Expose
    private BigDecimal averagePrice;
    @Expose
    private BigDecimal totalRevenue;

    public CategoryViewDto() {
    }

    public CategoryViewDto(String category, BigInteger productsCount, BigDecimal averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigInteger getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(BigInteger productsCount) {
        this.productsCount = productsCount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
