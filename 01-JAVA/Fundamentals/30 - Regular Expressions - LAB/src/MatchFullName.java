import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchFullName {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        Pattern pat = Pattern.compile("\\b[A-Z][a-z_]+ [A-Z][a-z]+");
        Matcher matcher = pat.matcher(text);
        while (matcher.find()) {
            System.out.print(matcher.group(0) + " ");
        }


    }
}
