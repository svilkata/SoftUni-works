import java.util.Scanner;

public class SumNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += Integer.parseInt(sc.nextLine());
        }

        System.out.println(sum);
    }
}
