package animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Animal> animals = new ArrayList<>();

        while (!"Beast!".equals(input)) {
            String[] params = sc.nextLine().split("\\s+");
            Animal animalCurr;

            try {
                if (input.equals("Dog")) {
                    animalCurr = new Dog(params[0], Integer.parseInt(params[1]), params[2]);
                } else if (input.equals("Cat")) {
                    animalCurr = new Cat(params[0], Integer.parseInt(params[1]), params[2]);
                } else if (input.equals("Frog")) {
                    animalCurr = new Frog(params[0], Integer.parseInt(params[1]), params[2]);
                } else if (input.equals("Kittens")) {
                    animalCurr = new Kitten(params[0], Integer.parseInt(params[1]));
                } else { //TomCat
                    animalCurr = new Tomcat(params[0], Integer.parseInt(params[1]));
                }

                animals.add(animalCurr);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            input = sc.nextLine();
        }

        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }


    }
}
