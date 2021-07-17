package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private Object[] elements = new Object[1];
    private int size = 0;

    @Override
    public boolean add(E element) {
        elements[size] = element;
        size++;

        //increase the size of the list
        if (size >= elements.length) {
            Object[] newElements = new Object[size * 2];
            for (int i = 0; i <elements.length; i++) {
                newElements[i] = elements[i];
            }

            elements = newElements;
        }

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        return false;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public E set(int index, E element) {
        elements[index] = element;
        return element;
    }

    @Override
    public E remove(int index) {
        E removedElement = (E) elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        return removedElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public boolean contains(E element) {
        return false;
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
