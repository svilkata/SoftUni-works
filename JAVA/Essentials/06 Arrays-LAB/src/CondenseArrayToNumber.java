import java.util.Arrays;
import java.util.Scanner;

public class CondenseArrayToNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] num = Arrays.stream(sc.nextLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();

        while (num.length>1) {
            int[] condensed = new int[num.length-1];

            for (int i = 0; i < condensed.length; i++) {
                condensed[i] = num[i] + num[i+1];
            }

            num = Arrays.copyOf(condensed, condensed.length);
        }

        System.out.println(num[0]);
    }
}
