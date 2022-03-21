package com.example.demo.entities.users;


import com.example.demo.exceptions.ValidationException;
import com.google.gson.annotations.Expose;

/**
 * Validates the data for registering a user.
 * <p>
 * Email must be
 * Password must be
 **/
public class RegisterDTO {
    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private String confirmPassword;
    @Expose
    private String fullName;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void validate() {
        int indexOfAt = this.email.indexOf("@");
        int indexOfDot = this.email.indexOf(".");
        if (indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot)
            throw new ValidationException("Email must contain @ or .");

        //TODO: Validate password
        if (!this.password.equals(confirmPassword)) {
            throw new ValidationException("Password and confirm password must match");
        }



    }
}
