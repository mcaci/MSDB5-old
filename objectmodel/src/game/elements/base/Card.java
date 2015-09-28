package game.elements.base;

/**
 * Created by nikiforos on 23/08/15.
 */
public class Card {

    private final CardSuit cardSuit;
    private final CardNumber cardNumber;

    public Card(CardNumber cardNumber, CardSuit cardSuit) {
        this.cardNumber = cardNumber;
        this.cardSuit = cardSuit;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public boolean isValid() {
        return cardSuit != null && cardNumber != null;
    }

    @Override
    public String toString() {
        return shortVersion();
    }

    private String shortVersion() {
        return "<" + cardNumber.name() + "," + cardSuit.name() + ">";
    }

    /**
     * regular toString version, longer, good for in depth debugging
     *
     * @return
     */
    private String normalVersion() {
        return "<" + cardNumber + "," + cardSuit + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (getCardSuit() != card.getCardSuit()) return false;
        return getCardNumber() == card.getCardNumber();
    }

    @Override
    public int hashCode() {
        int result = getCardSuit() != null ? getCardSuit().hashCode() : 0;
        result = 31 * result + (getCardNumber() != null ? getCardNumber().hashCode() : 0);
        return result;
    }
}
