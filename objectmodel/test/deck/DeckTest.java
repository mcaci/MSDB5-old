package deck;

import deck.card.Card;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 23/08/15.
 */
public abstract class DeckTest {

    private AbstractDeck testObjectAbstractDeck;

    public AbstractDeck getTestObjectAbstractDeck() {
        return testObjectAbstractDeck;
    }

    public void setTestObjectAbstractDeck(AbstractDeck testObjectAbstractDeck) {
        this.testObjectAbstractDeck = testObjectAbstractDeck;
    }

    @Test
    public void testNotNullDeck() throws Exception {
        assertNotNull(testObjectAbstractDeck);
        assertNotNull(testObjectAbstractDeck.getDeck());
    }

    @Test
    public void testDeckSize() throws Exception {
        int actualDeckSize = testObjectAbstractDeck.getDeck().size();
        int nominalDeckSize = testObjectAbstractDeck.getSize();

        checkSize(nominalDeckSize, "Nominal");
        checkSize(actualDeckSize, "Actual");
        assertTrue("actual size should be no greater than nominal", actualDeckSize <= nominalDeckSize);
    }

    private void checkSize(int deckSize, String sizeType) {
        assertTrue(sizeType + " size of deck should be greater than 0", deckSize > 0);
    }

    @Test
    public void testDeckHasContent() throws Exception {
        Queue<Card> cards = testObjectAbstractDeck.getDeck();
        checkSize(cards.size(), "Actual");
        for (Card card : cards) {
            assertTrue(card.isValid());
        }
    }

    @Test
    public void testAllCardsAreDifferent() throws Exception {
        Queue<Card> cards = testObjectAbstractDeck.getDeck();
        checkSize(cards.size(), "Actual");

        Object[] cardArray = cards.toArray();
        for (int i = 0; i < cardArray.length; i++) {
            Object cardToSearch = cardArray[i];
            for (int j = i + 1; j < cardArray.length - 1; j++) {
                Object cardInSearch = cardArray[j];
                boolean cardIsFound = cardToSearch.equals(cardInSearch);
                String message = cardToSearch + " in index " + i + " was found in deck in index " + j;
                assertFalse(message, cardIsFound);
            }
        }
    }
}