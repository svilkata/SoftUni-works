package ListUtilities;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static class Person implements Comparable<Person> {
        public int rating;

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.rating, o.rating);
        }
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();

        ListUtils.sort(people);

    }
}
