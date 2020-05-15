import java.util.Scanner;

public class PalindromeIntegers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!"END".equals(input)) {
            //int positiveIntNum = Integer.parseInt(input);

            if (isPalindrome(input)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }


            input = sc.nextLine();
        }
    }

    private static boolean isPalindrome(String positiveIntNum) {
        int middle = positiveIntNum.length() / 2;

        for (int i = 0; i < middle; i++) {
            if (positiveIntNum.charAt(i) != positiveIntNum.charAt(positiveIntNum.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }
}
