import java.util.Scanner;

public class PrintNumbersInReverseOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = sc.nextInt();
        }

        for (int i = 0; i < numbers.length / 2 ; i++) {
            int oppositeIndex = numbers.length - i -1;
            int oldNumbersI = numbers[i];
            numbers[i] = numbers[oppositeIndex];
            numbers[oppositeIndex] = oldNumbersI;
        }

        for (int i = 0; i < numbers.length ; i++) {
            System.out.println(numbers[i]);
        }

//        for (int i = numbers.length-1; i >=0 ; i--) {
//            System.out.print(numbers[i]);
//            if (i != 0) {
//                System.out.print(" ");
//            }
//        }
    }
}
