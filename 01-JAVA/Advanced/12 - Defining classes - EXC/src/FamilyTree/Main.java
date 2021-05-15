package FamilyTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String firstLine = reader.readLine();

        String namePattern = "[A-Z0-9][a-z0-9]+\\s[A-Z0-9][a-z0-9]+";

        Person mainPerson = createPersonByPattern(firstLine, namePattern);

        List<ManualPair> pairs = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        String line;
        while (!"End".equals(line = reader.readLine())) {
            String[] tokens = line.split("\\s-\\s");

            switch (tokens.length) {
                case 2:
                    ManualPair pair = new ManualPair(tokens[0], tokens[1]);//parent - child
                    pairs.add(pair);
                    break;
                case 1: // when we have names and birthday together for some person
                    tokens = tokens[0].split("\\s+");
                    String name = tokens[0] + " " + tokens[1];
                    String birthDate = tokens[2];

                    Person person = new Person(name, birthDate);
                    people.add(person);

                    //допълваме нашия човек ако докато е празно името или роождения ден
                    if ((name.equals(mainPerson.getName()) || birthDate.equals(mainPerson.getBirthDate()))
                            && mainPerson.hasNullFields()) {
                        updateMainPerson(mainPerson, name, birthDate);
                    }
                    break;
            }
        }

        FamilyTree familyTree = new FamilyTree();
        familyTree.setMainPerson(mainPerson);

        for (ManualPair pair : pairs) {
            if (pair.getKeyParent().equals(mainPerson.getName()) || pair.getKeyParent().equals(mainPerson.getBirthDate())) {
                people.stream()
                        .filter(p -> p.getName().equals(pair.getValueChild()) || p.getBirthDate().equals(pair.getValueChild()))
                        .findFirst()
                        .ifPresent(child -> familyTree.addChild(child));
            } else if (pair.getValueChild().equals(mainPerson.getName()) || pair.getValueChild().equals(mainPerson.getBirthDate())) {
                people.stream()
                        .filter(p -> p.getName().equals(pair.getKeyParent()) || p.getBirthDate().equals(pair.getKeyParent()))
                        .findFirst()
                        .ifPresent(parent -> familyTree.addParent(parent));
            }
        }

        System.out.println(familyTree);
    }

    private static void updateMainPerson(Person person, String name, String birthDate) {
        if (person.getName() == null) {
            person.setName(name);
        } else if (person.getBirthDate() == null) {
            person.setBirthDate(birthDate);
        }
    }

    private static Person createPersonByPattern(String value, String namePattern) {
        Person person = new Person();
        if (value.matches(namePattern)) {
            person.setName(value);
        } else {
            person.setBirthDate(value);
        }
        return person;
    }
}
