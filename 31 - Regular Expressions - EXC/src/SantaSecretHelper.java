import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SantaSecretHelper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int key = Integer.parseInt(sc.nextLine());

        String input = sc.nextLine();
        ArrayList<String> goodChildren = new ArrayList<>();
        while (!"end".equals(input)) {
            String decrypted = decryptedMessage(input, key);
            Pattern pattern = Pattern.compile("@([A-Za-z]+)[^@\\-!:>]*!([GN])!");
            Matcher matcher = pattern.matcher(decrypted);
            if (matcher.find()) {
                if (matcher.group(2).equals("G")) {
                    goodChildren.add(matcher.group(1));
                }
            }

            input = sc.nextLine();
        }

        for (String goodChild : goodChildren) {
            System.out.println(goodChild);
        }


    }

    private static String decryptedMessage(String input, int key) {
        StringBuilder decrMess = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            decrMess.append((char) ((int) input.charAt(i) - key));
        }
        return decrMess.toString();
    }
}
