package deck;

import deck.elements.Card;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by nikiforos on 23/08/15.
 */
public abstract class DeckTest {

    private Deck testObjectDeck;

    public Deck getTestObjectDeck() {
        return testObjectDeck;
    }

    public void setTestObjectDeck(Deck testObjectDeck) {
        this.testObjectDeck = testObjectDeck;
    }

    @Test
    public void testNotNullDeck() throws Exception {
        assertNotNull(testObjectDeck);
        assertNotNull(testObjectDeck.getDeck());
    }

    @Test
    public void testDeckSize() throws Exception {
        int actualDeckSize = testObjectDeck.getDeck().size();
        int nominalDeckSize = testObjectDeck.getSize();

        checkSize(nominalDeckSize, "Nominal");
        checkSize(actualDeckSize, "Actual");
        assertTrue("actual size should be no greater than nominal", actualDeckSize <= nominalDeckSize);
    }

    private void checkSize(int deckSize, String sizeType) {
        assertTrue(sizeType + " size of deck should be greater than 0", deckSize > 0);
    }

    @Test
    public void testDeckHasContent() throws Exception {
        Queue<Card> cards = testObjectDeck.getDeck();
        checkSize(cards.size(), "Actual");
        for (Card card : cards) {
            assertTrue(card.isValid());
        }
    }
}