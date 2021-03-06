package StackIteratorPck;

import java.util.Iterator;

public class CustomStack implements Iterable<Integer> {
    private Integer[] elements;
    private int currentIndex;

    public CustomStack() {
        this.elements = new Integer[16];
        this.currentIndex = 0;
    }

    public void push(Integer... elements) {
        for (Integer element : elements) {
            this.elements[this.currentIndex++] = element;
        }
    }

    public void pop() {
        if (this.elements[0] == null) {
            System.out.println("No elements");
        } else {
            this.elements[--this.currentIndex] = null;
        }
    }

    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {
            int index = currentIndex;
            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public Integer next() {
                return elements[--index];
            }
        };
    }
}
