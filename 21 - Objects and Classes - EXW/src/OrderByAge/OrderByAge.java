package OrderByAge;

public class OrderByAge {
    private String name;
    private String ID;
    private int age;

    public OrderByAge(String name, String ID, int age) {
        this.name = name;
        this.ID = ID;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        String result = String.format("%s with ID: %s is %d years old.", this.name, this.ID, this.age);
        return result;
    }
}
