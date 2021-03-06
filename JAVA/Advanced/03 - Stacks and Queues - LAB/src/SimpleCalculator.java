import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split("\\s+");
        Deque<String> stack = new ArrayDeque<>();
        Collections.addAll(stack, tokens); // добавяме колекцоята tokens в празната колекция stack

        while (stack.size() > 1) {
            int first = Integer.valueOf(stack.pop());
            String operator = stack.pop();
            int second = Integer.valueOf(stack.pop());

            switch (operator) {
                case "+":
                    stack.push(String.valueOf(first + second));
                    break;
                case "-":
                    stack.push(String.valueOf(first - second));
                    break;
            }
        }

        System.out.println(stack.pop());


    }
}
