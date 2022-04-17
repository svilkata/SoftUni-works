package com.soft_uni.jsonprocessing.model.dto.UserDto;

import com.google.gson.annotations.Expose;
import com.soft_uni.jsonprocessing.model.dto.ProductDto.ProductWithBuyerDto;

import java.util.Set;

public class UserSellerDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Set<ProductWithBuyerDto> soldProducts;

    public UserSellerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductWithBuyerDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductWithBuyerDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
