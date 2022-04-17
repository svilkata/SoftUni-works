package com.soft_uni.jsonprocessing.model.dto.ProductDto;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Set;

public class SoldProductsDto {
    @Expose
    private int count;
    @Expose
    private List<ProductNameAndPriceDto> products;

    public SoldProductsDto() {
    }

    public SoldProductsDto(int count, List<ProductNameAndPriceDto> products) {
        this.count = count;
        this.products = products;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductNameAndPriceDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNameAndPriceDto> products) {
        this.products = products;
    }
}
