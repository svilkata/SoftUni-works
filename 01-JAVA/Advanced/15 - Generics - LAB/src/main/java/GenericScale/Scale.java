package GenericScale;

public class Scale<T extends Comparable<T>> {
    private T left;
    private T right;

    public Scale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getHeavier() {
        int result = this.left.compareTo(this.right); //0 -1 1

        if (result == 0) {
            return null;
        }
        if (result > 0) {
            return this.left;
        }
        return this.right;
    }
}
