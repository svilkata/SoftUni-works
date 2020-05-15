import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftUniBarIncome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pattern orderPattern = Pattern.compile(
                "%(?<name>[A-Z][a-z]+)%([^|$%.]*)<(?<product>\\w+)>([^|$%.\\d]*)\\|" +
                        "(?<qtity>[0-9]+)\\|([^|$%.\\d]*)" + "(?<price>\\d+.?\\d*)\\$");

        double totalIncome = 0.0;
        String input = sc.nextLine();
        while (!"end of shift".equals(input)) {
            Matcher orderMatcher = orderPattern.matcher(input);
            if (orderMatcher.find()) {
                String name = orderMatcher.group("name");
                String product = orderMatcher.group("product");
                int quantity = Integer.parseInt(orderMatcher.group("qtity"));
                double unitPrice = Double.parseDouble(orderMatcher.group("price"));
                double totalPrice = quantity * unitPrice;
                System.out.println(String.format("%s: %s - %.2f", name, product, totalPrice));
                totalIncome += totalPrice;
            }
            input = sc.nextLine();
        }

        System.out.println(String.format("Total income: %.2f", totalIncome));
    }
}
