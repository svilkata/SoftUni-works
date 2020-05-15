import java.util.Scanner;

public class Samples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = new int[]{1, 2, 3, 4, 5, 6};
        int min = numbers[0];

        for (int n : numbers) {
            if (n < min) {
                min = n;
            }
        }

        System.out.println(min);
    }
}
