package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {
        while (hasParent(index) && less(parent(index), elements.get(index))) {
            int parentAt = getParentAt(index);
            Collections.swap(this.elements, parentAt, index);
            index = parentAt;
        }
    }

    private int getParentAt(int index) {
        return (index - 1) / 2;
    }

    private boolean less(E parent, E child) {
        int result = parent.compareTo(child);
        if (result < 0) {
            return true;
        }

        return false;
    }

    private E parent(int index) {
        return this.elements.get((index - 1) / 2);
    }

    private boolean hasParent(int index) {
        if (index == 0) {
            return false;
        }

        return true;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.elements.get(0);
    }

    private void ensureNonEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException("Heap is empty upon peek/poll attempt");
        }
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E removedElement = this.elements.get(0);
        Collections.swap(this.elements, 0, this.size() - 1);
        this.elements.remove(this.elements.size() - 1);
        this.heapifyDown(0);

        return removedElement;
    }

    /*function will demote the element at a given index until it has no children
     or it is greater than its both children. The first check will be our loop condition*/
    private void heapifyDown(int index) {
        while (index < this.elements.size() / 2) {
            int childLeftOrRightIndex = 2 * index + 1;
            int childRightIndex = 2 * index + 2;

            //ако левия клон е по-малък от десния, до десния по-голям изплува нагоре
            if (childRightIndex < this.elements.size() &&
                    less(this.elements.get(childLeftOrRightIndex), this.elements.get(childRightIndex))) {
                childLeftOrRightIndex += 1;
            }

            //ако детето стане първия елемент с нулев индекс
            if (less(this.elements.get(childLeftOrRightIndex), this.elements.get(index))) {
                break;
            }

            Collections.swap(this.elements, index, childLeftOrRightIndex);
            index = childLeftOrRightIndex;
        }
    }
}
