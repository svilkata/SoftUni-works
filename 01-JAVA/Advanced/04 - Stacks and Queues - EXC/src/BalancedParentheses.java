import java.util.ArrayDeque;
import java.util.Scanner;

public class BalancedParentheses {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> stackFirstHalf = new ArrayDeque<>();

        String[] elements = sc.nextLine().split("");

        if (elements.length % 2 == 1) {
            System.out.println("NO");
            return;
        }


        for (int i = 0; i < elements.length / 2; i++) {
            switch (elements[i]) {
                case "(":
                    stackFirstHalf.push(")");
                    break;
                case "[":
                    stackFirstHalf.push("]");
                    break;
                case "{":
                    stackFirstHalf.push("}");
                    break;
            }

        }

        for (int i = elements.length / 2; i < elements.length; i++) {
            String a = elements[i];
            String b = stackFirstHalf.pop();
            if (!a.equals(b)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
