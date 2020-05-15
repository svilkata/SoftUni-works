import java.util.Scanner;

public class SignOfInteger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        int number = Integer.parseInt(input);

        printSign(number);
    }

    private static void printSign(int number) {
        if (number > 0) {
            System.out.println("The number " + number + " is positive.");
        } else if (number < 0){
            System.out.println("The number " + number + " is negative.");
        } else {
            System.out.println("The number " + number + " is zero.");
        }
    }
}
