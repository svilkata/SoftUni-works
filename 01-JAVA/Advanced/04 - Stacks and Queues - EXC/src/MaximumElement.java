import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class MaximumElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        ArrayDeque<Integer> numbers = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split("\\s+");

            switch (tokens[0]) {
                case "1":
                    numbers.push(Integer.parseInt(tokens[1]));
                    break;
                case "2":
                    numbers.pop();
                    break;
                case "3":
                    if (!numbers.isEmpty()) {
                        System.out.println(Collections.max(numbers));
                    }

                    break;
            }
        }
    }
}
