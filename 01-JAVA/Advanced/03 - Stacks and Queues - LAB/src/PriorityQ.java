import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQ {
    public static void main(String[] args) {
        PriorityQueue<Integer> numbers = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue).reversed());
        numbers.offer(13);
        numbers.offer(73);
        numbers.offer(-5);
        numbers.offer(0);

        while (!numbers.isEmpty()) {
            System.out.println(numbers.poll());
        }
    }
}
