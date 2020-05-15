import java.util.Scanner;

public class MorseCodeTranslator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(" \\| ");
        String[] english = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                ",", ".", "?" };
        String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
                ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----",
                "--..--", ".-.-.-", "..--.." };


        StringBuilder fromMorseToEnglish = new StringBuilder();
        for (String s : input) {//words
            String[] tokens = s.split(" ");
            for (int i1 = 0; i1 < tokens.length; i1++) {
                String symbol = tokens[i1]; //letters in each word
                for (int i = 0; i < morse.length; i++) {
                    String mors = morse[i];
                    if (mors.equals(symbol)) {
                        fromMorseToEnglish.append(english[i].toUpperCase());
                        break;
                    }
                }
            }
            fromMorseToEnglish.append(" ");
        }

        System.out.println(fromMorseToEnglish.toString());
    }
}
