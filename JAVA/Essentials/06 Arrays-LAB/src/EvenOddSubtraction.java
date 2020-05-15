import java.util.Arrays;
import java.util.Scanner;

public class EvenOddSubtraction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //int[] numbers = new int[]{3, 5, 7, 9, 11};

        int evenSum = 0;
        int oddSUm = 0;
        int[] numbers = Arrays.stream(sc.nextLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();

        for (int n : numbers) {
            if (n % 2 == 0) {
                evenSum += n;
            } else {
                oddSUm += n;
            }
        }

        System.out.println(evenSum - oddSUm);
    }
}
