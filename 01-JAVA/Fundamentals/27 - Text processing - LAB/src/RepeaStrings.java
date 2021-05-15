import java.util.Arrays;
import java.util.Scanner;

public class RepeaStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] words = sc.nextLine().split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
        result+= repeatWord(words[i]);
        }

        String[] repeatWords;
        repeatWords = Arrays.stream(words)
                .map(word -> repeatWord(word))
                .toArray(String[]::new);

        System.out.println(String.join("", repeatWords));
    }

    private static String repeatWord(String word) {
        int times = word.length();
        String result = "";
        for (int i = 0; i < times; i++) {
            result += word;
        }
        return result;
    }
}
