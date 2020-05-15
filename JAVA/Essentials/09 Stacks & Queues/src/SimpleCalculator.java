import java.beans.PropertyEditorSupport;
import java.util.ArrayDeque;
import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(" ");
        ArrayDeque<Integer> digits = new ArrayDeque<>();

        for (int i = 0; i < input.length; i++) {
            if (Character.isDigit(input[i].charAt(0))) {
                digits.push(Integer.parseInt(input[i]));
            } else if (input[i].charAt(0) == '+') {
                addOrSubstract(digits, input[i + 1], '+');
                i++;
            } else if (input[i].charAt(0) == '-') {
                addOrSubstract(digits, input[i + 1], '-');
                i++;
            }
        }

        System.out.println(digits.peek());
    }

    private static void addOrSubstract(ArrayDeque<Integer> digits, String s, char operation) {
        Integer left = digits.pop();
        Integer right = Integer.parseInt(s);

        Integer result = 0;
        if (operation == '+') {
            result = left + right;
        } else {
            result = left - right;
        }

        digits.push(result);

    }
}

