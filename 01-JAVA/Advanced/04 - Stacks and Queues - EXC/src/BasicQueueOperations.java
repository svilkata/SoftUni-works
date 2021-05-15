import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class BasicQueueOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tokens = sc.nextLine().split("\\s+");
        String[] elements = sc.nextLine().split("\\s+");
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < Integer.parseInt(tokens[0]); i++) {
            queue.offer(Integer.parseInt(elements[i]));
        }

        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
            queue.poll();
        }

        if (!queue.isEmpty()) {
            boolean consistsElement = false;
            int minElement = Collections.min(queue);
            for (int i = 0; i < queue.size(); i++) {
                if (Integer.parseInt(tokens[2]) == queue.poll()) {
                    System.out.println(true);
                    consistsElement = true;
                    break;
                }
            }

            if (!consistsElement) {
                System.out.println(minElement);
            }
        } else {
            System.out.println(0);
        }

    }
}
