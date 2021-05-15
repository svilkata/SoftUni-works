import java.util.Arrays;
import java.util.Scanner;

public class MagicSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = Arrays.stream(sc.nextLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int targetNum = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numbers.length - 1; i++) {
            int firstNum = numbers[i];

            for (int j = i+1; j < numbers.length; j++) {
                int secondNum = numbers[j];
                if (firstNum + secondNum== targetNum) {
                    System.out.println(firstNum + " " + secondNum);
                }
            }
        }
    }
}
