package com.example.spotifyplaylistapp.model.dto.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBindingDto {
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters inclusive")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters inclusive")
    @NotBlank(message = "Password cannot be empty")
    private String password;

    public UserLoginBindingDto() {
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
