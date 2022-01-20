import java.util.Arrays;
import java.util.Scanner;

public class RecursiveArraySum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(x -> Integer.parseInt(x))
                .toArray();


        int sum2 = sumNumbers(arr, arr.length - 1);

        System.out.println(sum2);


    }

    private static int sumNumbers(int[] numbers, int index) {
        if (index < 0) {
            return 0;
        }
        return numbers[index] + sumNumbers(numbers, index - 1);
    }
}
