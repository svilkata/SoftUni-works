import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterByAge {
    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        List<Person> people = new ArrayList<>();

        while (n-- > 0) {
            String[] tokens = sc.nextLine().split(", ");
            people.add(new Person(tokens[0], Integer.parseInt(tokens[1])));
        }

        Map<String, Predicate<Person>> predicateMap = new HashMap<>();

        String ageCondition = sc.nextLine();
        int age = Integer.parseInt(sc.nextLine());
        predicateMap.put("younger", person -> person.getAge() <= age);
        predicateMap.put("older", person -> person.getAge() >= age);

        Map<String, Consumer<Person>> consumerMap = new HashMap<>();

        consumerMap.put("name", person -> System.out.println(person.getName()));
        consumerMap.put("age", person -> System.out.println(person.getAge()));
        consumerMap.put("name age", person -> System.out.println(person.getName() + " - " + person.getAge()));

        String format = sc.nextLine();

        people.stream()
                .filter(predicateMap.get(ageCondition))
                .forEach(consumerMap.get(format));




    }
}
