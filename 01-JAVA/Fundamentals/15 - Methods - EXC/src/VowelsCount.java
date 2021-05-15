import java.util.Scanner;

public class VowelsCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        System.out.println(countVowels(input));
    }

    private static int countVowels(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (isVowel(input.charAt(i))) {
                count++;
            }
        }

        return count;
    }

    private static boolean isVowel(char charLetter) {
        //String check = charLetter + "";
        if (charLetter == 'a' || charLetter == 'e' || charLetter == 'i' || charLetter == 'o' || charLetter == 'u'
                || charLetter == 'A' || charLetter == 'E' || charLetter == 'I' || charLetter == 'O' || charLetter == 'U') {
            return true;
        } else {
            return false;
        }


    }
}
