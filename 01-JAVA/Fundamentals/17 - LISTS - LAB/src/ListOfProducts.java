import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListOfProducts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        List<String> products = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            products.add(sc.nextLine());
        }

        Collections.sort(products);
        printResult(products);
    }

    private static void printResult(List<String> result) {
        for (int i = 0; i < result.size(); i++) {
            String el = result.get(i);
            System.out.println(String.format("%d.", i+1) + el);
        }

    }
}
