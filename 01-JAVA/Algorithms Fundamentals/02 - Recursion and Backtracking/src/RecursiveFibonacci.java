import java.util.Scanner;

public class RecursiveFibonacci {
    public static Long[] storedFibbonachiNumbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        storedFibbonachiNumbers = new Long[n + 1];

        for (int i = 0; i < n+1; i++) { //запълваме си масива с нули
            storedFibbonachiNumbers[i] = 0L;
        }


        if (n >= 1) { //нулвеият и първият елемент от масива са 1
            storedFibbonachiNumbers[0] = 1L;
            storedFibbonachiNumbers[1] = 1L;
        } else { //когато n e нула, то дължината на масива е 1. Нулевият елемент на масива е 1
            storedFibbonachiNumbers[0] = 1L;
        }

        Long fib = fibonacci(n);
        System.out.println(fib);
//        System.out.println(fibonacci(50)); // This will hang!
    }

    static long fibonacci(int n) {
        if (n == 0) {
            return storedFibbonachiNumbers[0];
        } else if (n == 1) {
            return storedFibbonachiNumbers[1];
        } else {
            if (storedFibbonachiNumbers[n] > 0L) {
                return storedFibbonachiNumbers[n];
            }

            return storedFibbonachiNumbers[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
