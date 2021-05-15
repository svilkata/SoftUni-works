import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Test {
    public static Supplier<List<Person>> generateRandPeople = () -> {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            people.add(new Person(Character.toString(i), new Random().nextInt(100)));
        }
        return people;
    };



    public static class Person {
        private String id;
        private int age;

        public Person(String ID, int age) {
            this.id = id;
            this.age = age;
        }
    }

    public static List<Person> getByPredicate(Collection<Person> coll, Predicate<Person> predicate) {
        return coll.stream().filter(predicate).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        List<Person> people = generateRandPeople.get();
        List<Person> collect = people.stream().filter(person -> person.age < 19).collect(Collectors.toList());
        collect = getByPredicate(people, p -> p.age >= 19);
    }
}
