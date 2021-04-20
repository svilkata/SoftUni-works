package CardsWithPower;

public class Card {
    private CardSuit suit;
    private CardPower power;

    public Card(CardPower power, CardSuit suit) {
        this.suit = suit;
        this.power = power;
    }

    private int calculatePower() {
        return this.power.getValue() + this.suit.getValue();
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d",
                this.power.toString(), this.suit.toString(), this.calculatePower());
    }
}
