package deck;

import card.Card;
import card.CardNumber;
import card.CardSuit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static gameinfo.GameInfoConstants.MAX_DECK_SIZE;

/**
 * Created by nikiforos on 29/08/15.
 */
public class DeckFactory {

    private Deck createdDeck;

    public DeckFactory() {
        this(MAX_DECK_SIZE);
    }

    public DeckFactory(int realDeckSize) {
        this.createdDeck = new Deck();

        List<Card> localDeck = createLocalDeck();

        shuffleLocalDeck(localDeck);

        addLocalDeckElementsToRealDeck(localDeck, realDeckSize);
    }

    public Deck getCreatedDeck() {
        return this.createdDeck;
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

    private void addLocalDeckElementsToRealDeck(List<Card> localDeck, int realDeckSize) {
        final Queue<Card> actualDeckQueue = this.createdDeck.getDeck();
        if (realDeckSize > MAX_DECK_SIZE) {
            throw new IllegalArgumentException("The size of the deck, " + realDeckSize + ", cannot exceed the max of " +
                    "the deck: " + MAX_DECK_SIZE);
        }
        for (int i = 0; i < realDeckSize; i++) {
            actualDeckQueue.add(localDeck.get(i));
        }
    }

}
