package hand;

import card.Card;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikiforos on 29/08/15.
 */
public class Hand {

    private final List<Card> hand;

    public Hand() {
        this.hand = new LinkedList<>();
    }

    public void add(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getSize() {
        return hand.size();
    }

    @Override
    public String toString() {
        return "Hand{" +
                "hand=" + hand +
                ", size=" + this.getSize() +
                '}';
    }

}
