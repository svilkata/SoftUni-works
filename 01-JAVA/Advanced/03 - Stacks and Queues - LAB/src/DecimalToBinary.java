import java.util.ArrayDeque;
import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int number = Integer.parseInt(sc.nextLine());

        if (number == 0) {
            System.out.println(0);
            return;
        }

        while (number != 0) {
            stack.push(number % 2);
            number /= 2;
        }

//        while (!stack.isEmpty()) {
//////            System.out.print(stack.pop());
//////        }

        for (Integer elem : stack) {
            System.out.print(elem);
        }


    }
}
