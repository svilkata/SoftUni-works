import java.util.Scanner;

public class NumberSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int sum = 0;
        int number;
        int nMin = Integer.MAX_VALUE, nMax = Integer.MIN_VALUE;


        for (int i = 1; i <= n; i++) {
            number = Integer.parseInt(sc.nextLine());

            if (nMin > number) {
                nMin = number;
            }
            if (nMax < number) {
                nMax = number;
            }
        }

        System.out.println(String.format("Max number: %d%nMin number: %d", nMax, nMin));
    }
}
