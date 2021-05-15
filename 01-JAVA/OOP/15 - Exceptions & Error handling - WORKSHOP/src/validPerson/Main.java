package validPerson;

public class Main {
    public static void main(String[] args) {
        try {
//            Person person = new Person("SSS", "Petrovich", 5);
            Student student = new Student("Ivan Batkov", "abv.bg");

        } catch (InvalidPersonNameException zzz) {
            System.out.println("Exception thrown: " + zzz.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Exception thrown: " + ex.getMessage());
        }
    }
}
