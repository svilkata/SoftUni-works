import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HotPotato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> queue = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayDeque::new));

        int n = Integer.parseInt(sc.nextLine());


        while (queue.size() > 1) {
            int k = n;
            if (n > queue.size()) {
                if (n % queue.size() == 0) {
                    k = queue.size();
                } else {
                    k = n % queue.size();
                }
            }

            for (int i = 1; i < k; i++) {
                String skip = queue.poll();
                queue.offer(skip);
            }

            System.out.println("Removed " + queue.poll());
        }

        System.out.println("Last is " + queue.peek());
    }
}
