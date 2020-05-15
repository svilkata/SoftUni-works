import java.util.Scanner;

public class TopNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= n; i++) {
            if (isTopNumber(i)) {
                System.out.println(i);
            }

        }
    }

    private static boolean isTopNumber(int number) {
        int sum = 0;
        boolean hasOddDigit = false;

        while (number > 0) {
            sum += number % 10;
            if (number % 2 != 0) {
                hasOddDigit = true;
            }

            number /= 10;
        }

        if (sum % 8 == 0 && hasOddDigit) {
            return true;
        }

        return false;
    }
}
