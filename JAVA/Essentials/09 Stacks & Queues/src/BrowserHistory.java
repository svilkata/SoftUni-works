import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        ArrayDeque<String> history = new ArrayDeque<>();

        while (!input.equals("Home")) {
            boolean error = false;
            if (input.equals("back")) {
                if (history.size() < 2) {
                    System.out.println("no previous URLs");
                    error = true;
                } else {
                    history.pop();
                    System.out.println(history.peek());
                }
            } else {
                history.push(input);
                System.out.println(history.peek());
            }



            input = sc.nextLine();
        }

    }
}
