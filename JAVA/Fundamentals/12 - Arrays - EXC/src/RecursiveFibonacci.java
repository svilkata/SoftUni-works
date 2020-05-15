import java.util.Scanner;

public class RecursiveFibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        long fib = GetFibonacci(n);
        System.out.println(fib);
    }

    private static long GetFibonacci(int n) {
        if (n == 2 || n == 1) {
            return 1L;
        }

        if (n == 0) {
            return 0;
        }

        {
            return GetFibonacci(n - 1) + GetFibonacci(n - 2);
        }
    }
}
