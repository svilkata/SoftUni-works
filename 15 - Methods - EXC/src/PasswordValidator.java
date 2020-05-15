import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String password = sc.nextLine();

        isValidPass(password);

    }

    private static void isValidPass(String password) {
        if (isCountCharacters(password) && isOnlyLettersDigits(password) && isTwoDigitsMinimum(password)) {
            System.out.println("Password is valid");
        } else {
            if (!isCountCharacters(password)) {
                System.out.println("Password must be between 6 and 10 characters");
            }

            if (!isOnlyLettersDigits(password)) {
                System.out.println("Password must consist only of letters and digits");
            }

            if (!isTwoDigitsMinimum(password)) {
                System.out.println("Password must have at least 2 digits");
            }
        }
    }

    private static boolean isTwoDigitsMinimum(String password) {
        int countDigits = 0;
        for (int i = 0; i < password.length(); i++) {
            //String temp = password.charAt(i) + "";
            if (Character.isDigit(password.charAt(i))) {
                countDigits++;
            }
        }

        if (countDigits >= 2) {
            return true;
        }

        return false;
    }

    private static boolean isOnlyLettersDigits(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (!((password.charAt(i) >= 48 && password.charAt(i) <= 57)
                    || (password.charAt(i) >= 65 && password.charAt(i) <= 90)
                    || (password.charAt(i) >= 97 && password.charAt(i) <= 122))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCountCharacters(String password) {
        int countCharacters = password.length();
        if (countCharacters <= 10 && countCharacters >= 6) {
            //System.out.println();
            return true;
        } else {
            return false;
        }
    }
}
