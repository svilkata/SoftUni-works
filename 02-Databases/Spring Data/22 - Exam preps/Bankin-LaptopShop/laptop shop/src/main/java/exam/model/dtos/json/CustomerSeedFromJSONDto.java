package exam.model.dtos.json;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

public class CustomerSeedFromJSONDto {
    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    @Email
    private String email;

    private String registeredOn;

    private TownJsonDto town;

    public CustomerSeedFromJSONDto() {
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

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public TownJsonDto getTown() {
        return town;
    }

    public void setTown(TownJsonDto town) {
        this.town = town;
    }
}



