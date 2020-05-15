import java.util.Scanner;

public class Orders {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String product = sc.nextLine();
        int qtity = Integer.parseInt(sc.nextLine());

        totalPrice(product, qtity);
    }

    private static void totalPrice(String product, int qtity) {
        switch (product) {
            case "coffee": {
                System.out.println(String.format("%.2f", 1.5 * qtity));
                break;
            }
            case "water": {
                System.out.println(String.format("%.2f", 1.0 * qtity));
                break;
            }
            case "coke": {
                System.out.println(String.format("%.2f", 1.4 * qtity));
                break;
            }
            case "snacks": {
                System.out.println(String.format("%.2f", 2.0 * qtity));
                break;
            }
        }
    }
}

