import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoublyLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;

    private class ListNode {
        private int value;
        private ListNode next;
        private ListNode previous;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public void addFirst(int element) {
        ListNode node = new ListNode(element);

        if (this.size == 0) {
            this.head = node;
            this.tail = node;
        } else {
            node.next = this.head;
            this.head.previous = node;
            this.head = node;
        }

        this.size++;
    }

    public void addLast(int element) {
        ListNode node = new ListNode(element);

        if (this.size == 0) {
            this.head = node;
            this.tail = node;
        } else {
            node.previous = this.tail;
            this.tail.next = node;
            this.tail = node;
        }

        this.size++;
    }

    public int get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bound for length " + this.size);
        }

        ListNode node = null;
        if (index <= this.size / 2) {
            node = this.head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = this.tail;
            for (int i = this.size - 1; i > index; i--) {
                node = node.previous;
            }
        }

        return node.value;
    }

    public int removeFirst() {
        checkSize();

        ListNode node = this.head;
        this.head= node.next;

        if (this.head == null) {
            this.tail = null;
        } else {
            this.head.previous = null;
        }

        this.size--;

        return node.value;
    }

    public int removeLast() {
        checkSize();

        ListNode node = this.tail;
        this.tail= node.previous;

        if (this.tail == null) {
            this.head = null;
        } else {
            this.tail.next = null;
        }

        this.size--;

        return node.value;
    }

    public void forEach(Consumer<Integer> consumer){
        ListNode current = this.head;
        while (current != null) {
            consumer.accept(current.value);
            current = current.next;
        }
    }

    public int[] toArray(){
        ListNode current = this.head;
        int[] array = new int[this.size];

        int index = 0;
        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }

    private void checkSize() {
        if (this.size == 0) {
            throw new NoSuchElementException("No element in the list");
        }
    }

    public DoublyLinkedList() {
    }
}
