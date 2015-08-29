package deck.factory;

import card.Card;
import deck.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 29/08/15.
 */
public class RandomDeckFactoryTest {

    private final static int TEST_DECK_SIZE = 5; // If more than RandomDeckFactory.MAX_DECK_SIZE (40 for now) it will
    // fail all tests (with explanation)

    private RandomDeckFactory testRandomDeckFactory;
    private Deck testObjectDeck;

    @Before
    public void setUp() throws Exception {
        try {
            testRandomDeckFactory = new RandomDeckFactory(TEST_DECK_SIZE);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        testObjectDeck = testRandomDeckFactory.getCreatedDeck();
    }

    @Test
    public void testGetCreatedDeck() throws Exception {
        assertNotNull(testObjectDeck);
        assertEquals("Size doesn't correspond to test value " + TEST_DECK_SIZE, TEST_DECK_SIZE, testObjectDeck
                .getSize());
    }

    @Test
    public void testNotNullDeck() throws Exception {
        assertNotNull(testObjectDeck);
        assertNotNull(testObjectDeck.getDeck());
    }

    @Test
    public void testDeckSize() throws Exception {
        int deckSize = testObjectDeck.getSize();
        assertTrue("Deck size should be greater than 0", deckSize > 0);
    }

    @Test
    public void testDeckHasContent() throws Exception {
        Queue<Card> cards = testObjectDeck.getDeck();
        for (Card card : cards) {
            assertTrue(card.isValid());
        }
    }

    @Test
    public void testAllCardsAreDifferent() throws Exception {
        Queue<Card> cards = testObjectDeck.getDeck();

        Object[] cardArray = cards.toArray();
        System.out.println(cardArray.length);
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