package com.example.coffeeshop.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

//a class for reading data from the web form
public class UserRegisterBindingModelDTO {
    @Size(min = 5, max = 20)
    private String username;

    private String firstName;

    @Size(min = 5, max = 20)
    private String lastName;

    @Email
    private String email;

    @Size(min = 4)
    private String password;

    @Size(min = 4)
    private String confirmPassword;

    public UserRegisterBindingModelDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UserRegisterBindingModelDTO{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
