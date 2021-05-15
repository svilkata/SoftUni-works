package CustList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomList<E extends Comparable<E>> implements  Iterable<E>{
    private List<E> list;
    private int size = 0;

    public CustomList() {
        this.list = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public void add(E element) {
        this.size++;
        this.list.add(element);
    }

    public E remove(int index) {
        if (isNotINRange(index)) {
            return null;
        } else {
            this.size--;
            return list.remove(index);
        }
    }

    public boolean contains(E element) {
        return list.contains(element);
    }

    public void swap(int firstIndex, int secondIndex) {
        if (isNotINRange(firstIndex) || isNotINRange(secondIndex)) {
            System.out.println("FirstIndex/SecondIndex is not a valid index!");
        } else {
            E first = this.list.get(firstIndex);
            E second = this.list.get(secondIndex);

            this.list.set(firstIndex, second);
            this.list.set(secondIndex, first);
        }
    }

    public int countGreaterThan(E element) {
        int count = 0;
        for (E e : list) {
            if (e.compareTo(element) > 0) {
                count++;
            }
        }

        return count;
    }

    public E getMax() {
        E max = this.list.get(0);

        for (E current : this.list) {
            if (current.compareTo(max) > 0) {
                max = current;
            }
        }

        return max;
    }

    public E getMin() {
        E min = this.list.get(0);

        for (E current : this.list) {
            if (current.compareTo(min) < 0) {
                min = current;
            }
        }

        return min;
    }

    public E get(int index) {
        if (isNotINRange(index)) {
            System.out.println("Index is not valid");
            return null;
        }

        return this.list.get(index);
    }

    private boolean isNotINRange(int index) {
        return index < 0 || index >= this.list.size();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < list.size();
            }

            @Override
            public E next() {
                return list.get(index++);
            }
        };
    }
}
