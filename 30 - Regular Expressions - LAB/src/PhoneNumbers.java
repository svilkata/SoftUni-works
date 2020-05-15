import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class PhoneNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        Pattern pattern = Pattern.compile("\\+359([\\- ])2(\\1)\\d{3}(\\1)\\d{4}\\b");
        Matcher matcher = pattern.matcher(text);

        List<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
            System.out.println(matcher.groupCount());
        }

        System.out.println(matches.toString().replaceAll("[\\[\\]]",""));
    }
}
