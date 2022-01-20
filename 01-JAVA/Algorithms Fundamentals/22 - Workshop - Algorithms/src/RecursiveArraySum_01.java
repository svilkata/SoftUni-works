import java.util.Arrays;
import java.util.Scanner;

public class RecursiveArraySum_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(x-> Integer.parseInt(x)).toArray();

        int sum = sumArr(arr, 0);

        System.out.println(sum);


    }

    private static int sumArr(int[] arr, int index) {
        if (index >= arr.length) {
            return 0;
        }
        return arr[index] + sumArr(arr, index + 1);


    }
}
