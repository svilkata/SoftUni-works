import java.util.ArrayDeque;
import java.util.Scanner;

public class MatchingBrackets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> bracketindexes = new ArrayDeque<>();
        String expression = sc.nextLine();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ') {
                continue;
            }

            if (expression.charAt(i) == '(') {
                bracketindexes.push(i); // saves the opening Index
            } else if (expression.charAt(i) == ')') {
                int opening = bracketindexes.pop();
                String current = expression.substring(opening, i+1);

                System.out.println(current);
            }
        }

    }
}
