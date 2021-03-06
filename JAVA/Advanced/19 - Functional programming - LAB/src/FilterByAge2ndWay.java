import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterByAge2ndWay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        LinkedHashMap<String, Integer> people = new LinkedHashMap<>();

        while (n-- > 0) {
            String[] tokens = sc.nextLine().split(", ");
            people.put(tokens[0], Integer.parseInt(tokens[1]));
        }
        String condition = sc.nextLine();
        int age = Integer.parseInt(sc.nextLine());
        String format = sc.nextLine();

        Predicate<Integer> tester = createTester(condition, age);
        Consumer<Map.Entry<String, Integer>> printer = createPrinter(format);
        printFilteredStudent(people, tester, printer);
    }

    static Predicate<Integer> createTester(String condition, Integer age) {
        Predicate<Integer> tester = null;
        switch (condition) {
            case "younger":
                tester = x -> x <= age;
                break;
            case "older":
                tester = x -> x >= age;
                break;
        }
        return tester;
    }

    static Consumer<Map.Entry<String, Integer>> createPrinter(String format) {
        Consumer<Map.Entry<String, Integer>> printer = null;
        switch (format) {
            case "name age":
                printer = person -> System.out.printf("%s - %d%n", person.getKey(), person.getValue());
                break;
            case "name":
                printer = person -> System.out.printf("%s%n", person.getKey());
                break;
            case "age":
                printer = person -> System.out.printf("%d%n", person.getValue());
                break;

        }
        return printer;
    }

    static void printFilteredStudent(LinkedHashMap<String, Integer> people,
                                     Predicate<Integer> tester, Consumer<Map.Entry<String, Integer>> printer) {

        for (Map.Entry<String, Integer> person : people.entrySet()) {
            if (tester.test(people.get(person.getKey())))
                printer.accept(person);
        }
    }
}
