package com.example.battleships.models.dto.binding;

import javax.validation.constraints.*;

public class UserRegistrationDto {
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 10)
    private String username;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String fullName;

    @NotEmpty
    @Email
    private String email;

    @Size(min = 4)
    private String password;

    @Size(min = 4)
    private String confirmPassword;

    public UserRegistrationDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
