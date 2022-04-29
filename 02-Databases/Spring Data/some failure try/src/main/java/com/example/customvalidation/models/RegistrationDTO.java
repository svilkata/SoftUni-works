package com.example.customvalidation.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class RegistrationDTO {
    @Size(min = 2, message = "Username must be at least 2 symbols long.")
    private String username;


    private String password;
//    @Password2(minLength = 4, maxLength = 30, containsDigit = true,
//            containsLowercase = true, containsUppercase = true, containsSpecialSymbol = true)


    private String confirmPassword;

    @Email
    private String email;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, String confirmPassword, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
