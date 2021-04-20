package shoppingSpree;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Person> people = Arrays.stream(sc.nextLine().split(";"))
                .map(p -> {
                    String[] data = p.split("=");
                    return new Person(data[0], Double.parseDouble(data[1]));
                })
                .collect(Collectors.toList());

        List<Product> products = Arrays.stream(sc.nextLine().split(";"))
                .map(p -> {
                    String[] data = p.split("=");
                    return new Product(data[0], Double.parseDouble(data[1]));
                })
                .collect(Collectors.toList());

        String input = sc.nextLine();
        while (!input.equals("END")) {
            String[] data = input.split("\\s+");

            for (Person person : people) {
                if (person.getName().equals(data[0])) {
                    Product product = null;
                    for (Product p : products) {
                        if (p.getName().equals(data[1])) {
                            product = p;
                            break;
                        }
                    }

                    if (product != null) {
                        try {
                            person.buyProduct(product);
                            System.out.println(person.getName() + " bought " + product.getName());
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    break;
                }
            }
            input = sc.nextLine();
        }

        for (Person person : people) {
            System.out.println(person);
        }

    }
}
