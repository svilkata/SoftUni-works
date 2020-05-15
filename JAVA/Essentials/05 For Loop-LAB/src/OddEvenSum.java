import java.util.Scanner;

public class OddEvenSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int evenSum = 0, oddSum = 0;

        for (int i = 1; i <= n; i++) {

            if (i % 2 == 0) {
                evenSum+= Integer.parseInt(sc.nextLine());
            } else {
                oddSum+= Integer.parseInt(sc.nextLine());
            }
        }



        if (evenSum == oddSum) {
            System.out.printf("Yes%nSum = %d", evenSum);
        } else {
            System.out.printf("No%nDiff = %d", Math.abs(oddSum - evenSum));
        }
    }
}
