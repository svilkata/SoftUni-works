package EqualityLogicPckg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TreeSet<Person> treeSet = new TreeSet<>(new CompareByName());
        Set<Person> hashSet = new HashSet<>();
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" ");
            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]));
            boolean isAlreadyExisting = false;
            for (Person prs : hashSet) {
                if (prs.equals(person)) {
                    isAlreadyExisting = true;
                    break;
                }
            }

            if (!isAlreadyExisting) {
                treeSet.add(person);
                hashSet.add(person);
            }

        }


        System.out.println(treeSet.size());
        System.out.println(hashSet.size());

    }
}
