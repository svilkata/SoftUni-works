import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Orders {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        LinkedHashMap<String, Double> orders = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> remeberProducts = new LinkedHashMap<>();
        while (!"buy".equals(input)) {
            String[] tokens = input.split(" ");
            String productName = tokens[0];
            double priceForProduct = Double.parseDouble(tokens[1]);
            int qty = Integer.parseInt(tokens[2]);

            if (!remeberProducts.containsKey(productName)) {
                remeberProducts.putIfAbsent(productName, qty);
                orders.putIfAbsent(productName, qty * priceForProduct);
            } else {
                int oldTotalQtity = remeberProducts.get(productName);
                remeberProducts.put(productName, qty + oldTotalQtity);
                orders.put(productName, remeberProducts.get(productName) * priceForProduct);
            }

            input = sc.nextLine();
        }

        orders
                .forEach((k, v) -> System.out.println(String.format("%s -> %.2f", k, v )));



    }
}
