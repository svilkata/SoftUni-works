package Tuples;

public class Tuple<Item1, Item2, Item3> {
    private Item1 item1;
    private Item2 item2;
    private Item3 item3;

    public Tuple(Item1 item1, Item2 item2, Item3 item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public Item1 getItem1() {
        return item1;
    }

    public Item2 getItem2() {
        return item2;
    }

    public Item3 getItem3() {
        return item3;
    }

    @Override
    public String toString() {
        return this.item1 + " -> " + this.item2 + " -> " + this.item3;
    }
}
