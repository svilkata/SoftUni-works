package StrategyPatternPckg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<Person> byName = new TreeSet<>(new CompareByName());
        Set<Person> byAge = new TreeSet<>();

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" ");
            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]));
            byName.add(person);
            byAge.add(person);
        }



        for (Person person : byName) {
            System.out.println(person.getName() + " " + person.getAge());
        }

        for (Person person : byAge) {
            System.out.println(person.getName() + " " + person.getAge());
        }


    }
}
