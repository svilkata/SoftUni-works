public class Main {
    public static void main(String[] args) {
        Student st1 = new Student("1234", "Svilen", "Velikov");
        System.out.println(st1 instanceof Student); //true
        System.out.println(st1 instanceof Person); //true

        Person st2 = new Student("1234", "Svilen", "Velikov");
        System.out.println(st2 instanceof Student); //true
        System.out.println(st2 instanceof Person); //true
    }
}
