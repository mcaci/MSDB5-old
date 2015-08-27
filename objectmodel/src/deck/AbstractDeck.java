package deck;

import deck.card.Card;
import deck.card.CardNumber;
import deck.card.CardSuit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by nikiforos on 23/08/15.
 */
public abstract class AbstractDeck {

    private final Queue<Card> deck;
    private final int size;

    private final int maxDeckSize;

    public AbstractDeck(int size) {
        deck = new LinkedList<>();
        this.size = size;
        this.maxDeckSize = CardNumber.values().length * CardSuit.values().length;
        initializeDeck();
    }

    private void initializeDeck() {
        List<Card> localDeck = new LinkedList<>();
        final CardSuit[] suits = CardSuit.values();
        final CardNumber[] numbers = CardNumber.values();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                localDeck.add(new Card(numbers[i], suits[j]));
            }
        }

        Collections.shuffle(localDeck);

        for (int i = 0; i < size; i++) {
            deck.add(localDeck.get(i));
        }
    }

    public Queue<Card> getDeck() {
        return deck;
    }

    public int getSize() {
        return size;
    }
}
