import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractEmails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] words = input.split(" ");

        ArrayList<String> mails = new ArrayList<>();
        Pattern userPattern = Pattern.compile("(?<=\\s)[a-zA-Z0-9]+([-.]\\w*)*");
//        "^([A-Za-z0-9]+(\\.|\\-|_)?[A-Za-z0-9]+)$"
        Pattern domainPattern = Pattern.compile("^[a-zA-Z]+?([.-][a-zA-Z]*)*(\\.[a-z]{2,})$");
//"([a-zA-Z]+?([.-][a-zA-Z]*)*(\\.[a-z]{2,})";
//        ((?<=\s)[a-zA-Z0-9]+([-.]\w*)*@[a-zA-Z]+?([.-][a-zA-Z]*)*(\.[a-z]{2,}))

        for (int i = 0; i < words.length; i++) {
            if (!words[i].contains("@")) {
                continue;
            }
            String currWord = words[i];

            String user = currWord.substring(0, currWord.indexOf("@"));
            String domain = currWord.substring(currWord.indexOf("@") + 1);

            if (user.length() < 2 || domain.length() < 3) {
                continue;
            }
            if (domain.charAt(domain.length() - 1) == '.' && domain.length() > 1) {
                domain = domain.substring(0, domain.length() - 1);
            }

            Matcher userMatcher = userPattern.matcher(user);
            Matcher domainMatcher = domainPattern.matcher(domain);

            if (userMatcher.find() && domainMatcher.find()) {
                currWord = user + "@" + domain;
                mails.add(currWord);
            }
        }

        for (String mail : mails) {
            System.out.println(mail);
        }


    }
}
