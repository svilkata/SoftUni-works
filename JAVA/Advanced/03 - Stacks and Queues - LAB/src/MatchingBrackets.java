import java.util.ArrayDeque;
import java.util.Scanner;

public class MatchingBrackets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> openingSymbols = new ArrayDeque<>();

        String expression = sc.nextLine();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            if (current == '(') {
                openingSymbols.push(i);
            } else if (current == ')') {
                int beginIndex = openingSymbols.pop();
                String subExpression = expression.substring(beginIndex, i + 1);
                System.out.println(subExpression);
            }
        }


    }
}
