import java.util.Scanner;

public class Equal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(" ");
        int[] numbers = new int[input.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        int index = -1;

        for (int i = 0; i < numbers.length; i++) {
            int currrenIndex = i;
            int leftSum = 0;
            int rightSum = 0;

            for (int j = currrenIndex - 1; j >= 0; j--) {
                leftSum += numbers[j];
            }

            for (int j = currrenIndex + 1; j < numbers.length; j++) {
                rightSum += numbers[j];
            }

            if (rightSum == leftSum) {
                index = currrenIndex;
                break;
            }

        }

        if (index != -1) {
            System.out.println(index);
        } else {
            System.out.println("no");
        }
    }

}
