package com.example.coffeeshop.model.view;

public class UserViewModelDTO {
    private String username;
    private Integer countOfOrders;

    public UserViewModelDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCountOfOrders() {
        return countOfOrders;
    }

    public void setCountOfOrders(Integer countOfOrders) {
        this.countOfOrders = countOfOrders;
    }
}
