package pizzaCallories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] data = sc.nextLine().split("\\s+");
        Pizza pizza = null;

        try {
            pizza = new Pizza(data[1], Integer.parseInt(data[2]));
            data = sc.nextLine().split("\\s+");
            Dough dough = new Dough(data[1], data[2], Double.parseDouble(data[3]));
            pizza.setDough(dough);

            String input = sc.nextLine();
            while (!"END".equals(input)){
                data = input.split("\\s+");
                Topping topping = new Topping(data[1], Double.parseDouble(data[2]));
                pizza.addTopping(topping);

                input = sc.nextLine();
            }

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(pizza);


    }
}
