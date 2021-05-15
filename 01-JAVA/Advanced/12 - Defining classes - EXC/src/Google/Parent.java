package Google;

public class Parent {
    private String name;
    private String birthday;

    public Parent(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name + " " + this.birthday;
    }

    public String getBirthday() {
        return birthday;
    }
}
