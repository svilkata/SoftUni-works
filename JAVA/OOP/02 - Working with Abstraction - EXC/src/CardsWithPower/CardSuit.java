package CardsWithPower;

public enum CardSuit {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private final int value;

    CardSuit(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
