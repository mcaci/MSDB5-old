package deck.elements;

/**
 * Created by nikiforos on 23/08/15.
 */
public class Card {

    private final Suit suit;
    private final Number number;

    public Card(Number number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }


    public Suit getSuit() {
        return suit;
    }

    public Number getNumber() {
        return number;
    }

    public boolean isValid() {
        return suit != null && number != null;
    }

    @Override
    public String toString() {
        return "<" + number + "," + suit + ">";
    }
}
