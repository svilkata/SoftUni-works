package com.soft_uni.jsonprocessing.model.dto.UserDto;

import com.google.gson.annotations.Expose;
import com.soft_uni.jsonprocessing.model.dto.ProductDto.SoldProductsDto;

public class UserNameAgeAndSoldProductsDto {
    private Long id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private SoldProductsDto soldProducts;

    public UserNameAgeAndSoldProductsDto() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SoldProductsDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductsDto soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
