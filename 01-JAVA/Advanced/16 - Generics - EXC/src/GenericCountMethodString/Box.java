package GenericCountMethodString;

public class Box<E extends Comparable<E>> implements Comparable<E>{
    private E data;

    public E getData() {
        return data;
    }

    public Box(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.data.getClass().getName() + ": " +this.data;
    }

    @Override
    public int compareTo(E o) {
        return data.compareTo(o);
    }
}
