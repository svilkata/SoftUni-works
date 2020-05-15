import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class CountCharsInString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] input = sc.nextLine().toCharArray();
        LinkedHashMap<Character, Integer> letters = new LinkedHashMap<>();
        for (char letter : input) {
            if (letter == ' ') {
                continue;
            }
            letters.putIfAbsent(letter, 0);
            int count = letters.get(letter);
            letters.put(letter, count + 1);
        }

        letters
                .entrySet()
                .forEach(p -> System.out.println(String.format("%c -> %d", p.getKey(), p.getValue())));


    }
}
