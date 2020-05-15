import java.util.Scanner;

public class LeftRightSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int leftSum = 0, rightSum = 0;

        for (int i = 1; i <= n; i++) {
            leftSum += Integer.parseInt(sc.nextLine());
        }

        for (int i = n + 1; i <= 2 * n; i++) {
            rightSum += Integer.parseInt(sc.nextLine());
        }

        if (leftSum == rightSum) {
            System.out.println("Yes, sum = " + leftSum);
        } else {
            System.out.println("No, diff = " + Math.abs(leftSum - rightSum));
        }
    }
}
