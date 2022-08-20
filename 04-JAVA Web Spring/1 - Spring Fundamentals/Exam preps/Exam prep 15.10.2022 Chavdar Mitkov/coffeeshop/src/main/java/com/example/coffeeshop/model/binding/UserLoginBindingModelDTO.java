package com.example.coffeeshop.model.binding;

import javax.validation.constraints.Size;

public class UserLoginBindingModelDTO {
    @Size(min = 5, max = 20, message = "My username error message")
    private String username;

    @Size(min = 3, message = "My password error message")
    private String password;

    public UserLoginBindingModelDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
