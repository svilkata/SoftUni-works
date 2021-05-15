import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class Samples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        ArrayDeque<Integer> stack = new ArrayDeque<>();
//        int size = stack.size();
//        boolean isEmpty = stack.isEmpty();
//        boolean exists = stack.contains(2);
//
//        String[] children = sc.nextLine().split(" ");
//        int n = Integer.parseInt(sc.nextLine());

        ArrayDeque<String> queue = new ArrayDeque<>();
        System.out.println(queue.size());

        queue.offer("One");
        System.out.println(queue.size());

        queue.remove();
        System.out.println(queue.size());

        queue.offer("Two");
        System.out.println(queue.size());


//        Collections.addAll(queue, children);
//        for (String child : children)
//            queue.offer(child);



    }
}
