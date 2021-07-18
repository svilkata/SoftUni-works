package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private final int DEFAULT_CAPACITY = 7;
    private int head;
    private int tail;
    private int size;

    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.head = this.elements.length / 2;
        this.tail = this.head;
    }

    @Override
    public void add(E element) {
        this.addLast(element);
    }

    private void grow() {
        Object[] newElements = new Object[this.elements.length * 2];
        int middle = newElements.length / 2 - 1;

        for (int i = middle; i < middle + this.size; i++) {
            newElements[i] = this.elements[this.head++];
        }

        this.head = middle;
        this.tail = middle + this.size - 1;
        this.elements = newElements;

//        for (int i = this.elements.length / 2 + this.head - 1;
//             i < middle + (this.tail - this.elements.length / 2); i++) {
//            newElements[i] = this.elements[this.head];
//        }

    }

    @Override
    public void offer(E element) {
        this.addFirst(element);
    }

    @Override
    public void addFirst(E element) {
        if (this.size == 0) {
            this.elements[this.head] = element;
        } else {
            if (this.head == 0) {
                this.grow();
            }
            this.elements[--this.head] = element;
        }
    }

    @Override
    public void addLast(E element) {
        if (this.size == 0) {
            this.elements[this.tail] = element;
        } else {
            if (this.tail == this.elements.length - 1) {
                this.grow();
            }
            this.elements[++this.tail] = element;
        }

        this.size++;
    }

    @Override
    public void push(E element) {
        this.addLast(element);
    }

    @Override
    public void insert(int index, E element) {
        this.validateIndex(index);

        if (index == 0) {
            this.addFirst(element);
        } else if (index == this.size) {
            this.addLast(element);
        } else {
            if (this.tail == this.elements.length - 1) {
                this.grow();
            }
            this.tail++;

            for (int i = this.tail; i > this.head + index; i--) {
                this.elements[i] = this.elements[i - 1];
            }

            this.elements[this.head + index] = element;
        }

    }

    private void validateIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public void set(int index, E element) {
        this.validateIndex(index);
        this.elements[this.head + index] = element;
    }

    @Override
    public E peek() {
        if (this.size == 0) {
            return null;
        }

        return (E) this.elements[this.head];
    }

    @Override
    public E poll() {
        return this.removeFirst();
    }

    @Override
    public E pop() {
        return this.removeLast();
    }

    @Override
    public E get(int index) {
        this.validateIndex(index);
        return (E) this.elements[this.head + index];
    }

    @Override
    public E get(Object object) {
        for (int i = this.head; i <= this.tail; i++) {
            if (this.elements[i].equals(object)) {
                return (E) this.elements[i];
            }
        }
        return null;
    }

    @Override
    public E remove(int index) {
        this.validateIndex(index);
        if (this.isEmpty()) {
            return null;
        }


        E elementToRemove = null;

        if (index == 0) {
            elementToRemove = (E) this.elements[this.head];
            this.head++;
            return elementToRemove;
        } else if (index == this.size - 1) {
            elementToRemove = (E) this.elements[this.tail];
            this.tail--;
        } else { //I remove in the middle
            for (int i = this.head + index; i < this.tail; i++) {
                this.elements[i] = this.elements[i + 1];
            }


            this.elements[this.tail] = null;

            this.tail--;
        }

        return elementToRemove;
    }

    @Override
    public E remove(Object object) {
        for (int i = this.head; i <= this.tail; i++) {
            if (this.elements[i].equals(object)) {
                return (E) this.remove(i);
            }
        }

        return null;
    }

    @Override
    public E removeFirst() {
        return this.remove(0);
    }

    @Override
    public E removeLast() {
        return this.remove(this.size - 1);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        Object[] newElements = new Object[this.size];
        int rr = 0;
        for (int i = head; i <= tail ; i++) {
            newElements[rr++] = this.elements[i];
        }

        this.elements = newElements;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = head;

            @Override
            public boolean hasNext() {
                return current <= tail;
            }

            @Override
            public E next() {
                return (E) elements[current++];
            }
        };
    }
}
