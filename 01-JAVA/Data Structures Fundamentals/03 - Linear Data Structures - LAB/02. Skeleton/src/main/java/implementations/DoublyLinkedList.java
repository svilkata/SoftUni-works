package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public Node<E> getHead() {
        return head;
    }

    public static class Node<E> {
        private E element;          // Must have
        private Node<E> next;       // Must have
        private Node<E> previous;   // Additional

        public Node(E element, Node<E> next, Node<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public Node setNext(Node<E> next) {
            this.next = next;
            return this;
        }

        public Node setPrevious(Node<E> previous) {
            this.previous = previous;
            return this;
        }
    }



    @Override
    public void addFirst(E element) {

    }

    @Override
    public void addLast(E element) {
        Node<E> newNnode = new Node<>(element, null, tail);
        if (head == null) {
            head = newNnode;
        }

        if (tail != null) {
            tail.setNext(newNnode);
        }

        tail = newNnode;

        size++;
    }

    public void remove(Node<E> node) {
        if (head == node) {
            head = node.getNext();
        }

        if (tail == node) {
            tail = node.getPrevious();
        }

        if (node.getPrevious() != null) {
            node.getPrevious().setNext(node.getNext());
        }

        if (node.getNext() != null) {
            node.getNext().setPrevious(node.getPrevious());
        }
        
        size--;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
