import java.util.Scanner;

public class AddAndSubtract {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        substract(add(sc), sc);
    }

    private static void substract(int sumOfFirstTwo, Scanner sc) {
        int thirdNum = Integer.parseInt(sc.nextLine());

        System.out.println(sumOfFirstTwo - thirdNum);
    }

    private static int add(Scanner sc) {
        return Integer.parseInt(sc.nextLine()) + Integer.parseInt(sc.nextLine());
    }
}
