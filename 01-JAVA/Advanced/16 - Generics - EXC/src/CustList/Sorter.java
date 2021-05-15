package CustList;

public class Sorter {
    public static <E extends Comparable<E>> void sort(CustomList<E> list) {
        for (int i = 0; i < list.getSize(); i++) {
            E current = list.get(i);
            for (int j = i+1; j < list.getSize(); j++) {
                E target = list.get(j);
                if (current.compareTo(target) > 0) {
                    list.swap(i, j);
                }
            }
        }

    }
}
