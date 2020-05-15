import java.util.Scanner;

public class TextFilter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] bannedWords = sc.nextLine().split(", ");
        String text = sc.nextLine();

        for (int i = 0; i < bannedWords.length; i++) {
            String replacement = generateReplacement(bannedWords[i].length());
            text = text.replace(bannedWords[i], replacement);
        }

        System.out.println(text);
    }

    private static String generateReplacement(int wordLength) {
        String[] parts = new String[wordLength];

        for (int i = 0; i < wordLength; i++) {
            parts[i] = "*";
        }

        return String.join("", parts);

    }
}
