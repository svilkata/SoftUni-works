import java.util.Scanner;

public class TribonacciSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nNum = Integer.parseInt(sc.nextLine());
        printTrib(nNum);
    }

    private static void printTrib(int nnum) {
        for (int i = 1; i <= nnum ; i++) {
            System.out.print(tribonacciRecursion(i) + " ");
        }
    }

    private static int tribonacciRecursion(int num) {
        if (num == 0) {
            return 0;
        } else if (num == 1 || num == 2) {
            return 1;
        } else {
            return tribonacciRecursion(num - 1) + tribonacciRecursion(num - 2) + tribonacciRecursion(num - 3);
        }
    }
}
