import java.util.Scanner;

public class BalancedBrackets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nLines = Integer.parseInt(sc.nextLine());
        char inputChar;
        int brOpenBracket = 0;
        int brCloseBracket = 0;
        boolean isBalanced = true;

        for (int i = 1; i <= nLines; i++) {
            String input = sc.nextLine();
            if (input.length() == 1) {
                inputChar = input.charAt(0);

                if (inputChar == '(') {
                    brOpenBracket++;

                } else if (inputChar == ')') {
                    brCloseBracket++;
                    if (brOpenBracket - brCloseBracket != 0) {
                        System.out.println("UNBALANCED");
                        return;
                    }

                }
            }
        }

        if (brOpenBracket == brCloseBracket) {
            System.out.println("BALANCED");
        } else {
            System.out.println("UNBALANCED");
        }

    }
}
