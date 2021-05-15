package definePerson;

public class Citizen implements Person {
    private String name;
    private int age;

    public Citizen(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String sayHello(){
        return "Hello";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
