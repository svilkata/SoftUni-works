package person;

public class Child extends Person {

    public Child(String name, int age) {
        super(name, age);
    }

    public void makeOlder(int age) {
        this.setAge(this.getAge() + age);
    }
}
