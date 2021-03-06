import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistoryUpgrade {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();

        String navigation = sc.nextLine();
        String currentURL = "";

        while (!"Home".equals(navigation)) {
            if (navigation.equals("back")) {
                if (!stack.isEmpty()) {
                    currentURL = stack.pop();
                } else {
                    System.out.println("no previous URLs");
                    navigation = sc.nextLine();
                    continue;
                }
            } else {
                if (currentURL.length() > 0) {
                    stack.push(currentURL);
                }
                currentURL = navigation;
            }

            System.out.println(currentURL);

            navigation = sc.nextLine();
        }
    }
}

