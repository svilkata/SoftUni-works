import java.util.Arrays;
import java.util.Scanner;

public class EvenOddSubtraction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arrNumbers = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(e -> Integer.parseInt(e)).toArray();
        int sumEven = 0, sumOdd = 0;

        for (int i = 0; i < arrNumbers.length; i++) {
            if (arrNumbers[i] % 2 == 0) {
                sumEven += arrNumbers[i];
            } else {
                sumOdd+= arrNumbers[i];
            }
        }

        System.out.println(sumEven - sumOdd);
    }
}
