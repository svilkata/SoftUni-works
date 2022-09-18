package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.validation.FieldMatch;
import bg.softuni.mobilele.model.validation.UniqueUserEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(firstField = "password",
        secondField = "confirmPassword",
        message = "Passwords do not match" //we override here the default error message
)
public class UserRegisterDto {
    @NotEmpty(message = "User email should be provided") //we override here the default error message
    @Email(message = "User email should be valid") //we override here the default error message
    @UniqueUserEmail(message = "User email should be unique")  //we override here the default error message
    private String email;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @NotEmpty
    @Size(min = 5)
    private String password;

    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
