import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture {
    public static void main(String[] args) {
        ArrayList<String> furniture = new ArrayList<>();
        double finalPrice = 0.0;
        Pattern pattern = Pattern.compile(">>(\\w+)<<(\\d+\\.?\\d*)!(\\d+)");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!"Purchase".equals(input)) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String name = matcher.group(1);
                double price = Double.parseDouble(matcher.group(2));
                int quantity = Integer.parseInt(matcher.group(3));
                furniture.add(name);
                finalPrice += price * quantity;
            }
            input = sc.nextLine();
        }

        System.out.println("Bought furniture:");

        for (String s : furniture) {
            System.out.println(s);
        }

        System.out.println(String.format("Total money spend: %.2f", finalPrice));


    }
}
