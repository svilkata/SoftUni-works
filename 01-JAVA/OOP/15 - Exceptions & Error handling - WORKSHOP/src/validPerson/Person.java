package validPerson;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
    }

    private void setAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age should be in the range [0...120]");
        }
        this.age = age;
    }

    private void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("The last name can not be null or empty");
        } else if (lastName.isBlank()) {
            throw new IllegalArgumentException("The last name can not be null or empty");
        }
        this.lastName = lastName;
    }

    private void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("The first name can not be null or empty");
        } else if (firstName.isBlank()) {
            throw new IllegalArgumentException("The first name can not be null or empty");
        }
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }
}
