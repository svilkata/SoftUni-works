import java.util.Scanner;

public class FloatingEquality {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());

        if (Math.abs(a - b) >= 0.000001) {
            System.out.println("False");
        } else {
            System.out.println("True");
        }

    }
}
