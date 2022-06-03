package bg.softuni.springmvc.web.model;

public class UserDto {
    private String fname;
    private String lname;

    public String getFname() {
        return fname;
    }

    public UserDto setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public String getLname() {
        return lname;
    }

    public UserDto setLname(String lname) {
        this.lname = lname;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}
