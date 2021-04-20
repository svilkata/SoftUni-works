package wildFarm;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Mammal, Food> mammals = new LinkedHashMap<>();

        String input = sc.nextLine();
        int countLines = 0;
        Mammal mammal = null;
        while (!"End".equals(input)) {
            if (countLines++ % 2 == 0) { //even lines
                String[] tokens = input.split("\\s+");
                String animalType = tokens[0];
                String animalName = tokens[1];
                Double animalWeigth = Double.parseDouble(tokens[2]);
                String animalLivingregion = tokens[3];
                switch (animalType) {
                    case "Mouse":
                        mammal = new Mouse(animalType, animalName, animalWeigth, animalLivingregion);
                        break;
                    case "Zebra":
                        mammal = new Zebra(animalType, animalName, animalWeigth, animalLivingregion);
                        break;
                    case "Tiger":
                        mammal = new Tiger(animalType, animalName, animalWeigth, animalLivingregion);
                        break;
                    case "Cat":
                        String catBreed = tokens[4];
                        mammal = new Cat(animalType, animalName, animalWeigth, animalLivingregion, catBreed);
                        break;
                }
//        {AnimalType} {AnimalName} {AnimalWeight} {AnimalLivingRegion} [{CatBreed} = Only if it is a cat]
            } else { //odd lines
                String[] tokens = input.split("\\s+");
                String foodType = tokens[0];
                Integer quantityEaten = Integer.parseInt(tokens[1]);
                Food food = null;
                if (foodType.equals("Vegetable")) {
                    food = new Vegetable(quantityEaten);
                } else {
                    food = new Meat(quantityEaten);
                }
                mammal.eat(quantityEaten, foodType);
                mammal.makeSound();
                mammals.put(mammal, food);
                System.out.println(mammal);
//                FoodType and quantity
            }


            input = sc.nextLine();
        }

//        for (Mammal mm : mammals) {
//            System.out.println(mm.toString());
//        }

    }
}
