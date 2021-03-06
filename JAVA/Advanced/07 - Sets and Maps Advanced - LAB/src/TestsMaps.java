import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TestsMaps {
    public static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Pesho 12,Gosho 13,Ivan 42
        Map<String, Person> strings = Arrays.stream(sc.nextLine().split(","))
                .map(str -> {
                    String[] tokens = str.split("\\s+");
                    return new Person(tokens[0], Integer.parseInt(tokens[1]));
                })
                .collect(Collectors.toMap(p->p.name, p -> p));

        for (Map.Entry<String, Person> entry : strings.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().name + " "+ entry.getValue().age);
        }
    }
}
