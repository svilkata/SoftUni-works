import java.util.Scanner;

public class RecursiveFibonacci {
    private static long[] memoization;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        memoization = new long[n + 2];

        System.out.println(fib(n+1));
    }

    private static long fib(int n) {
        if (n <= 2) {
            return 1;
        }

        if (memoization[n] != 0) {
            return memoization[n];
        }

        return memoization[n] = fib(n - 1) + fib(n - 2);
    }
}
