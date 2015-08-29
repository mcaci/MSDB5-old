package deck;

import card.Card;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by nikiforos on 23/08/15.
 */
public class Deck {

    private final Queue<Card> deck;

    public Deck() {
        this.deck = new LinkedList<>();
    }

    public Queue<Card> getDeck() {
        return deck;
    }

    public int getSize() {
        return deck.size();
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                ", size=" + this.getSize() +
                '}';
    }

}
