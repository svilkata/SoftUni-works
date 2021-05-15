package birthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Birthable> citizens_pets = new ArrayList<>();

        String input = sc.nextLine();
        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            if (tokens[0].equals("Citizen")) {
                Birthable citizen = new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
                citizens_pets.add(citizen);
            } else if (tokens[0].equals("Pet")) {
                Birthable pet = new Pet(tokens[1], tokens[2]);
                citizens_pets.add(pet);
            } else {
                //Robot case - do nothing "Robot <model> <id>"
            }
            input = sc.nextLine();
        }

        String yearToChek = sc.nextLine();

        boolean isMatch = false;
        for (Birthable citizenOrPet : citizens_pets) {
            if (citizenOrPet.getBirthDate().endsWith(yearToChek)){
                System.out.println(citizenOrPet.getBirthDate());
                isMatch = true;
            }
        }

        if (!isMatch) {
            System.out.println("<no output>");
        }
    }
}
