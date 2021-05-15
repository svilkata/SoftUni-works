import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Samples {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] children = scanner.nextLine().split("\\s+");
        ArrayDeque<String> queue = new ArrayDeque<>();
        for (int i = children.length - 1; i >= 0; i--) {
            String child = children[i];
            queue.offer(child);
        }


    }
}
