import java.util.Scanner;

public class RefactoringPrimeChecker {
    public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;

            for (int delitel = 2; delitel < i; delitel++) {
                if (i % delitel == 0) {
                    isPrime = false;
                    break;
                }
            }
            System.out.printf("%d -> %b%n", i, isPrime);
        }

    }
}
