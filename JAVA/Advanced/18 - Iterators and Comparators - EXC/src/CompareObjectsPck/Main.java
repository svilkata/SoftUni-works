package CompareObjectsPck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        List<Person> personList = new LinkedList<>();
        while (!(line = reader.readLine()).equals("END")) {
            String[] tokens = line.split(" ");
            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            personList.add(person);
        }

        int equalCount = 0;
        int notEqualCount = 0;
        int index = Integer.parseInt(reader.readLine()) - 1;
        Person target = personList.get(index);
        for (Person person : personList) {
            if (person.compareTo(target) == 0) {
                equalCount++;
            } else {
                notEqualCount++;
            }
        }

        if (equalCount - 1 == 0) {
            System.out.println("No matches");
        } else {
            System.out.printf("%d %d %d", equalCount, notEqualCount, personList.size());
        }

    }
}
