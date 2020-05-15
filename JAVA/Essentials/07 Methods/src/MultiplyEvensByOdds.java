import java.util.Scanner;

public class MultiplyEvensByOdds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        System.out.println(getMultiplyEven_Odds(n));
    }

    private static int getMultiplyEven_Odds(int n) {
        int evenSum = getSumEvenDigits(n);
        int oddSum = getSumOddDigits(n);
        return evenSum * oddSum;
    }

    private static int getSumOddDigits(int n) {
        int sumOdd = 0;
        n = Math.abs(n);
        while (n > 0) {
            int currentDigit = n % 10;
            if (currentDigit % 2 != 0) {
                sumOdd += currentDigit;
            }
            n = n / 10;
        }
        return sumOdd;
    }

    private static int getSumEvenDigits(int n) {
        int sumEven = 0;
        n = Math.abs(n);
        while (n > 0) {
            int currentDigit = n % 10;
            if (currentDigit % 2 == 0) {
                sumEven += currentDigit;
            }
            n = n / 10;
        }

        return sumEven;
    }
}
