import java.util.Scanner;

public class SignIntegerNumbers {
    static void printSign(int num){
        if(num>0){
            System.out.println(String.format("The number %d is positive.", num));
        } else if (num < 0) {
            System.out.println(String.format("The number %d is negative.", num));
        } else {
            System.out.println(String.format("The number %d is zero.", num));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = Integer.parseInt(sc.nextLine());

        printSign(number);

    }
}
