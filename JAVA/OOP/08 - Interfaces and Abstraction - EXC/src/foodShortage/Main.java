package foodShortage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfPeople = Integer.parseInt(sc.nextLine());
        List<Person> allPeople = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            String[] tokens = sc.nextLine().split("\\s+");
            if (tokens.length == 4) { //citizen case
                Person citizen = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
                allPeople.add(citizen);
            } else { //rebellion person
                Person rebel = new Rebel(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                allPeople.add(rebel);
            }
        }

        String input = sc.nextLine();
        while (!"End".equals(input)) {
            for (Person personOrRebel : allPeople) {
                if (input.equals(personOrRebel.getName())) {
                    personOrRebel.buyFood();
                }
            }
            input = sc.nextLine();
        }

        System.out.println(allPeople.stream()
                .mapToInt(s -> s.getFood())
                .sum());


    }
}
