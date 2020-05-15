import java.util.Arrays;
import java.util.Scanner;

public class CondenseArrayToNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbersArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();

        while (numbersArr.length > 1) {
            int[] condensedArr = new int[numbersArr.length - 1];

            for (int i = 0; i < condensedArr.length; i++) {
                condensedArr[i] = numbersArr[i] + numbersArr[i + 1];
            }

            numbersArr = condensedArr;

        }

        System.out.println(numbersArr[0]);

    }
}
