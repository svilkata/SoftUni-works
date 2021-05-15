import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class MathPotato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] children = sc.nextLine().split(" ");
        int n = Integer.parseInt(sc.nextLine());

        ArrayDeque<String> queue = new ArrayDeque<>();

        Collections.addAll(queue, children);

        int cycles = 1;
        while (queue.size() > 1) {
            for (int i = 1; i < n; i++) {
                String child = queue.remove();
                queue.offer(child);
            }

            String name = queue.peek();
            if (!isPrime(cycles)) {
                System.out.println("Removed " + name);
                queue.remove();
            } else {
                System.out.println("Prime " + name);
            }
            cycles++;
        }

        String name = queue.peek();
        System.out.println("Last is " + name);

    }

    private static boolean isPrime(int number) {

        if (number == 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
