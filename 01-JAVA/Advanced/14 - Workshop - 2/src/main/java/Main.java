import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addFirst(5);
        doublyLinkedList.addFirst(2);


        int[] ints = doublyLinkedList.toArray();

        System.out.println(doublyLinkedList.get(4));
    }

}
