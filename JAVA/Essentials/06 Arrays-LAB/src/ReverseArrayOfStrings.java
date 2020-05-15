import java.util.Arrays;
import java.util.Scanner;

public class ReverseArrayOfStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] numbers = sc.nextLine().split(" ");

        for (int i = 0; i < numbers.length / 2 ; i++) {
            int oppositeIndex = numbers.length - i -1;
            String oldNumbersI = numbers[i];
            numbers[i] = numbers[oppositeIndex];
            numbers[oppositeIndex] = oldNumbersI;
        }

        for (int i = 0; i < numbers.length ; i++) {
            System.out.print(numbers[i]);
            if (i != numbers.length-1) {
                System.out.print(" ");
            }
        }
    }
}
