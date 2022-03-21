package com.example.demo.entities.users;


import com.example.demo.exceptions.ValidationException;

/**
 * Validates the data for registering a user.
 * <p>
 * Email must be
 * Password must be
 **/
public class RegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;


    /**
     * commmandParts[0] is skipped
     *
     * @param commandParts all data read from the console
     */
    public RegisterDTO(String[] commandParts) {
        this.email = commandParts[1];
        this.password = commandParts[2];
        this.confirmPassword = commandParts[3];
        this.fullName = commandParts[4];

        this.validate();
    }

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

    private void validate() {
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
