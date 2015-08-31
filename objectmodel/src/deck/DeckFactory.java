package deck;

import card.Card;
import card.CardNumber;
import card.CardSuit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by nikiforos on 29/08/15.
 */
public class DeckFactory {

    private final static int MAX_DECK_SIZE = CardNumber.values().length * CardSuit.values().length;

    private Deck createdDeck;

    public DeckFactory() {
        this(MAX_DECK_SIZE);
    }

    public DeckFactory(int realDeckSize) {
        List<Card> localDeck = createLocalDeck();
        shuffleLocalDeck(localDeck);
        this.createdDeck = new Deck();
        final Queue<Card> actualDeck = this.createdDeck.getDeck();
        addLocalDeckElementsToRealDeck(actualDeck, localDeck, realDeckSize);
    }

    public Deck getCreatedDeck() {
        return this.createdDeck;
    }

    private void addLocalDeckElementsToRealDeck(Queue<Card> cardQueue, List<Card> localDeck, int realDeckSize) {
        if (realDeckSize > MAX_DECK_SIZE) {
            throw new IllegalArgumentException("The size of the deck, " + realDeckSize + ", cannot exceed the max of " +
                    "the deck: " + MAX_DECK_SIZE);
        }
        for (int i = 0; i < realDeckSize; i++) {
            cardQueue.add(localDeck.get(i));
        }
    }

    private List<Card> createLocalDeck() {
        List<Card> localDeck = new LinkedList<>();
        final CardSuit[] suits = CardSuit.values();
        final CardNumber[] numbers = CardNumber.values();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                localDeck.add(new Card(numbers[i], suits[j]));
            }
        }
        return localDeck;
    }

    private void shuffleLocalDeck(List<Card> localDeck) {
        Collections.shuffle(localDeck);
    }

}
