package dto.toNestedJSONFile;

public class CustomerOutputToJSONDto {
    private String firstName;
    private String lastName;
    private String email;
    private TownJsonDto town;

    public CustomerOutputToJSONDto() {
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

    public TownJsonDto getTown() {
        return town;
    }

    public void setTown(TownJsonDto town) {
        this.town = town;
    }
}



