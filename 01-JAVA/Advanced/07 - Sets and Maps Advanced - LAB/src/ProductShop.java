import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ProductShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Map<String, Double>> summary = new TreeMap<>();

        String input = "";
        while (!"Revision".equals(input = sc.nextLine())) {
            String[] tokens = input.split(",\\s+");

            String shop = tokens[0];
            String product = tokens[1];
            double price = Double.parseDouble(tokens[2]);

            if (!summary.containsKey(shop)) {
                summary.put(shop, new LinkedHashMap<>());
                summary.get(shop).put(product, price);
            } else {
                summary.get(shop).put(product, price); //price replacing
            }
        }

        for (Map.Entry<String, Map<String, Double>> entry : summary.entrySet()) {
            System.out.println(entry.getKey() + "->");
            for (Map.Entry<String, Double> entryInner : entry.getValue().entrySet()) {
                System.out.println(String.format("Product: %s, Price: %.1f", entryInner.getKey(), entryInner.getValue()));
            }
        }
    }
}
