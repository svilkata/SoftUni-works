import java.util.Arrays;
import java.util.Scanner;

public class ReverseArrayStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arrString = sc.nextLine().split(" ");

        for (int i = 0; i < arrString.length / 2; i++) {
            int oppositeIndex = arrString.length - i - 1;
            String oldNumbersI = arrString[i];
            arrString[i] = arrString[oppositeIndex];
            arrString[oppositeIndex] = oldNumbersI;
        }

        for (String element : arrString) {
            System.out.print(element + " ");
        }


    }
}
