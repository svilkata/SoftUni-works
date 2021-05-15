import java.util.ArrayDeque;
import java.util.Scanner;

public class Sample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> stack_queue = new ArrayDeque<>();
        stack_queue.push("a");
        stack_queue.push("b");
        stack_queue.push("c");

//        System.out.println(stack_queue.pop());
        System.out.println(stack_queue.poll());

//        for (String s : stack_queue) {
//            System.out.println(s);
//        }


    }
}
