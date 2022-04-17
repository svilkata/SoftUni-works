package com.example.json_ex.productsshop.entities.users;

import com.example.json_ex.productsshop.entities.products.SoldProductDTO;

import java.util.List;

public class UserWithSoldProductsDTO {
    private String firstName;
    private String lastName;
    private List<SoldProductDTO> itemsBought;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setItemsBought(List<SoldProductDTO> itemsBought) {
        this.itemsBought = itemsBought;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<SoldProductDTO> getItemsBought() {
        return itemsBought;
    }
}
