import java.util.Scanner;

public class DigitsLettersOther {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();


        StringBuilder letters = new StringBuilder();
        letters.delete(0, 4);
        letters.insert(2, "pesho");

        StringBuilder digits = new StringBuilder();
        StringBuilder symbols = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            if (Character.isDigit(current)) {
                digits.append(current);
            } else if (Character.isLetter(current)) {
                letters.append(current);
            } else {
                symbols.append(current);
            }
        }

       letters.delete(0, 4);
        System.out.println(digits.toString());
        System.out.println(letters.toString());
        System.out.println(symbols.toString());
    }
}
