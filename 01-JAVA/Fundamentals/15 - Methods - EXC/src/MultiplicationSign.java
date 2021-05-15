import java.util.Scanner;

public class MultiplicationSign {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());

        productIs(a, b, c);
    }

    private static void productIs(int a, int b, int c) {
        if (a == 0 || b == 0 || c ==0) {
            System.out.println("zero");
            return;
        }

        int brMinuses = 0;
            if (a < 0) {
            brMinuses++;
        }
        if (b < 0) {
            brMinuses++;
        }
        if (c < 0) {
            brMinuses++;
        }

        if (brMinuses % 2 == 0) {
            System.out.println("positive");
        } else {
            System.out.println("negative");
        }
    }
}
