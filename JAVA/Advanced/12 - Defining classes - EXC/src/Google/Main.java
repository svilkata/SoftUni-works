package Google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        List<Person> people = new ArrayList<>();
        while (!(line = reader.readLine()).equals("End")) {
            String[] tokens = line.split("\\s+");

//            •	"<Name> company <companyName> <department> <salary>"
//             •	"<Name> pokemon <pokemonName> <pokemonType>"
//          •	"<Name> parents <parentName> <parentBirthday>"
//          •	"<Name> children <childName> <childBirthday>"
//          •	"<Name> car <carModel> <carSpeed>"
            Person person = new Person(tokens[0]);

            boolean hasPerson = false;
            for (Person currentPerson : people) {
                if (currentPerson.getName().equals(tokens[0])) {
                    hasPerson = true;
                    person = currentPerson;
                    break;
                }
            }

            switch (tokens[1]) {
                case "company":
                    Company company = new Company(tokens[2], tokens[3], Double.parseDouble(tokens[4]));
                    person.setCompany(company);
                    break;
                case "pokemon":
                    Pokemon pokemon = new Pokemon(tokens[2], tokens[3]);
                    List<Pokemon> pokemons = person.getPokemons();
                    pokemons.add(pokemon);
                    person.setPokemons(pokemons);
                    break;
                case "parents":
                    Parent parent = new Parent(tokens[2], tokens[3]);
                    List<Parent> parents = person.getParents();
                    parents.add(parent);
                    person.setParents(parents);
                    break;
                case "children":
                    Child child = new Child(tokens[2], tokens[3]);
                    List<Child> childs = person.getChildrens();
                    childs.add(child);
                    person.setChildrens(childs);
                    break;
                case "car":
                    Car car = new Car(tokens[2], Integer.parseInt(tokens[3]));
                    person.setCar(car);
                    break;
            }



            if (!hasPerson) {
                people.add(person);
            }

        }

        String targetName = reader.readLine();
        StringBuilder sb = new StringBuilder();
        for (Person person : people) {
            if (person.getName().equals(targetName)) {

                sb.append(person.getName()).append(System.lineSeparator());

                sb.append("Company:").append(System.lineSeparator());
                if (person.getCompany() != null) {
                    sb.append(person.getCompany().toString()).append(System.lineSeparator());
                }

                sb.append("Car:").append(System.lineSeparator());
                if (person.getCar() != null) {
                    sb.append(person.getCar().toString()).append(System.lineSeparator());
                }

                sb.append("Pokemon:").append(System.lineSeparator());
                for (Pokemon pokemon : person.getPokemons()) {
                    sb.append(pokemon.toString()).append(System.lineSeparator());
                }

                sb.append("Parents:").append(System.lineSeparator());
                for (Parent parent : person.getParents()) {
                    sb.append(parent.toString()).append(System.lineSeparator());
                }

                sb.append("Children:").append(System.lineSeparator());
                for (Child child : person.getChildrens()) {
                    sb.append(child.toString()).append(System.lineSeparator());
                }
            }
        }

        System.out.println(sb);
    }
}
