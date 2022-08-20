package com.example.spotifyplaylistapp.model.dto.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegistrationBindingDto {
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters inclusive")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Email(message = "Email must be valid and have @")
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Email must be valid and have @")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters inclusive")
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters inclusive")
    @NotBlank(message = "confirmePassword cannot be empty")
    private String confirmPassword;

    public UserRegistrationBindingDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
