package sortByName_Age;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age) {
        this(firstName, lastName, age, 500.0);
    }

    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.validateName(firstName, "First");
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.validateName(lastName, "Last");
        this.lastName = lastName;
    }

    private void validateName(String toCheck, String fieldName) {
        if (toCheck.length() < 3) {
            throw new IllegalArgumentException(fieldName +" name cannot be less than 3 symbols");
        }
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        if (age < 1) {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 460) {
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
        this.salary = salary;
    }

    public void increaseSalary(double bonus) {
        double percents = bonus / 100.0;
        double multiplier = 1 + percents;

        if (this.age < 30) {
            multiplier = 1 + percents / 2.0;
        }

        this.setSalary(this.getSalary() * multiplier);
    }

    @Override
    public String toString() {
        return String.format("%s %s is %d years old.",
                this.firstName, this.lastName, this.age);
    }

    @Override
    public int compareTo(Person o) {
        int sComp = this.getFirstName().compareTo(o.getFirstName());

        if (sComp != 0) {
            return sComp;
        } else {
            return Integer.compare(this.getAge(), o.getAge());
        }
    }

    public String getSalaryString() {
        return String.format("%s %s gets %s leva", this.firstName, this.lastName,
                this.salary);
    }
}
