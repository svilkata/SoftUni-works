package animals;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    private void setName(String name) {
        validateString(name);
        this.name = name;
    }

    private void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    private void setGender(String gender) {
        this.validateString(gender);
        this.gender = gender;
    }

    private void validateString(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    public abstract String produceSound();

    @Override
    public String toString() {
        return String.format("%s%n%s %d %s",
                this.getClass().getSimpleName(),
                this.name,
                this.age,
                this.gender) + "\r\n" + this.produceSound();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
