import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostOffice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split("\\|");
        String word1 = input[0];
        String word2 = input[1];
        String[] word3 = input[2].split(" ");

        Pattern word1Pattern = Pattern.compile("([$#%*&])([A-Z]+)\\1");
        Matcher word1Matcher = word1Pattern.matcher(word1);
        String capitalLetters = "";
        if (word1Matcher.find()) {
            capitalLetters = word1Matcher.group(2);
        }

        LinkedHashMap<Character, Integer> matchedCapitalLetters = new LinkedHashMap<>();
        for (int i = 0; i < capitalLetters.length(); i++) {
            int currLetter = capitalLetters.charAt(i);
            String pattern2 = String.format("(%d%d):(\\d{2})", currLetter / 10, currLetter % 10);
            Pattern word2Pattern = Pattern.compile(pattern2);
            Matcher word2Matcher = word2Pattern.matcher(word2);
            if (word2Matcher.find()) {
                int numWordLength = Integer.parseInt(word2Matcher.group(2)) + 1;
                matchedCapitalLetters.put(capitalLetters.charAt(i), numWordLength);
            }
        }


        for (Map.Entry<Character, Integer> letter : matchedCapitalLetters.entrySet()) {
            String pattern3 = String.format("^(%c[^ ]{%d})$", letter.getKey(), letter.getValue() - 1);
            Pattern word3Pattern = Pattern.compile(pattern3);
            for (int i = 0; i < word3.length-1; i++) {
                Matcher word3Matcher = word3Pattern.matcher(word3[i]);
                if (word3Matcher.find()) {
                System.out.println(word3Matcher.group(1));
                }
            }

            Pattern word3_Pattern = Pattern.compile(String.format("^(%c[^ ]{%d})\\b", letter.getKey(), letter.getValue() - 1));
            Matcher word3Matcher = word3Pattern.matcher(word3[word3.length-1]);
            if (word3Matcher.find()) {
                System.out.println(word3Matcher.group(1));
            }


        }

    }
}
