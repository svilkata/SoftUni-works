import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseNumbersWithStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> stackNumbers = new ArrayDeque<>();
        int[] numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < numbers.length; i++) {
            stackNumbers.push(numbers[i]);
        }

        while (!stackNumbers.isEmpty()) {
            System.out.print(stackNumbers.pop() + " ");
        }

    }
}
