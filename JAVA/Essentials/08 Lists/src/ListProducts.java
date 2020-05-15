import java.util.*;

public class ListProducts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<String> products = new ArrayList<String>();

        for (int i = 1; i <= n; i++) {
            products.add(sc.nextLine());
        }

        Collections.sort(products);

        for (int i = 0; i < products.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, products.get(i)));
        }

    }
}
