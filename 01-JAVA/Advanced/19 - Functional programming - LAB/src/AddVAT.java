import java.util.Arrays;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class AddVAT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Prices with VAT:");
        UnaryOperator<Double> addVAT = price -> price * 1.20;

        Arrays.stream(sc.nextLine().split(", "))
                .mapToDouble(str -> addVAT.apply(Double.parseDouble(str)))
                .forEach(vat -> System.out.println(String.format("%.2f", vat)));
    }
}
