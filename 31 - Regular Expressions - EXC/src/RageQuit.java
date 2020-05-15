import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RageQuit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        Pattern pattern = Pattern.compile("([\\D]+)([0-9]+)");
        Matcher matcher = pattern.matcher(input);

        StringBuilder output = new StringBuilder();
        ArrayList<Character> uniqueSymbols = new ArrayList<>();
        while (matcher.find()) {
            if (Integer.parseInt(matcher.group(2)) == 0){
                continue;
            }
            String currMatchString = matcher.group(1).toUpperCase();
            for (int i = 0; i < currMatchString.length(); i++) {
                if (!uniqueSymbols.contains(currMatchString.charAt(i))) {
                    uniqueSymbols.add(currMatchString.charAt(i));
                }
            }

            int currMatchRepeat = Integer.parseInt(matcher.group(2));
            for (int i = 0; i < currMatchRepeat; i++) {
                output.append(currMatchString);
            }
        }

        System.out.println(String.format("Unique symbols used: %d", uniqueSymbols.size()));
        System.out.println(output.toString());
    }
}
