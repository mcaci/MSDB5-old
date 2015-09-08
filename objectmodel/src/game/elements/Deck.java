package game.elements;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by nikiforos on 23/08/15.
 */
public class Deck {

    public static final int DEFAULT_SIZE = CardNumber.values().length * CardSuit.values().length;

    private final Queue<Card> deck;

    public Deck() {
        this.deck = new LinkedList<>();
    }

    public Queue<Card> getDeck() {
        return deck;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "game=" + deck +
                ", size=" + this.deck.size() +
                '}';
    }

    public void addCardSet(List<Card> cardList, int deckSize) {
        if (deckSize > DEFAULT_SIZE) {
            throw new IllegalArgumentException("The size of the deck, " + deckSize + ", cannot exceed the max of " +
                    "the game: " + DEFAULT_SIZE);
        }
        this.deck.addAll(cardList.subList(0, deckSize));
    }

    public boolean isEmpty() {
        return this.deck.isEmpty();
    }
}
