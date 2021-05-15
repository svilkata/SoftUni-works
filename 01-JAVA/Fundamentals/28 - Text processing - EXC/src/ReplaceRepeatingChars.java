import java.util.Scanner;

public class ReplaceRepeatingChars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        StringBuilder result = new StringBuilder();

        char baseLetter = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char currLetter = input.charAt(i);
            if (baseLetter != currLetter) {
                result.append(baseLetter);
                baseLetter = currLetter;
            }
        }

        result.append(baseLetter);

        System.out.println(result);


    }
}
