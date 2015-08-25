package deck;

import deck.elements.Card;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by nikiforos on 23/08/15.
 */
public class Deck {

    private final Queue<Card> deck;
    private final int size;

    public Deck(int size) {
        deck = new LinkedList<>();
        this.size = size;
    }

    public Queue<Card> getDeck() {
        return deck;
    }

    public int getSize() {
        return size;
    }
}
