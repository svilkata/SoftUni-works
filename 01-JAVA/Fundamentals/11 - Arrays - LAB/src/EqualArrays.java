import java.util.Arrays;
import java.util.Scanner;

public class EqualArrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] firstArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int[] secondArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();

        int minLength = -1;
        int sumEqual = 0;
        boolean isEqual = true;

        if (firstArr.length == secondArr.length) {
            for (int i = 0; i < firstArr.length; i++) {
                if (firstArr[i] == secondArr[i]) {
                    sumEqual+=firstArr[i];
                }
                else {
                    System.out.printf("Arrays are not identical. Found difference at %d index.", i);
                    return;
                }
            }
        }
//        else {
//            if (firstArr.length > secondArr.length) {
//                minLength = secondArr.length;
//            } else {
//                minLength = firstArr.length;
//            }
//
//            for (int i = 0; i < minLength; i++) {
//                if (firstArr[i] == secondArr[i]) {
//                    keepIndexFirstDiffer = i;
//                    break;
//                }
//            }
//        }

        System.out.printf("Arrays are identical. Sum: %d", sumEqual);

    }
}
