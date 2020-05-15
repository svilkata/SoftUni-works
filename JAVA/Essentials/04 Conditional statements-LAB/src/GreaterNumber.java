import java.util.Scanner;

public class GreaterNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = Integer.parseInt(sc.nextLine()), b = Integer.parseInt(sc.nextLine());

        System.out.println(Math.max(a, b));
    }
}
