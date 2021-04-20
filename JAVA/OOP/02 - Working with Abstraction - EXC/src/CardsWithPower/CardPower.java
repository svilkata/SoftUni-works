package CardsWithPower;

public enum CardPower {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    private int value;

    private CardPower(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
