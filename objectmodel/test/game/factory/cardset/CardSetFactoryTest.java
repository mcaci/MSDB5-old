package game.factory.cardset;

import game.elements.base.Card;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 29/08/15.
 */
public abstract class CardSetFactoryTest {

    CardSetFactory cardSetFactoryTestObject;

    public abstract Collection<Card> getCardSet();

    @Test
    public void testOnSize() throws Exception {
        int deckSize = getCardSet().size();
        assertTrue("Deck size should be greater than 0", deckSize > 0);
    }

    @Test
    public void testOnContent() throws Exception {
        Collection<Card> cards = getCardSet();
        for (Card card : cards) {
            assertTrue(card.isValid());
        }
    }

    @Test
    public void testAllCardsAreDifferent() throws Exception {
        Collection<Card> cards = getCardSet();

        Object[] cardArray = cards.toArray();
        for (int i = 0; i < cardArray.length; i++) {
            Object cardToSearch = cardArray[i];
            for (int j = i + 1; j < cardArray.length; j++) {
                Object cardInSearch = cardArray[j];
                boolean cardIsFound = cardToSearch.equals(cardInSearch);
                String message = cardToSearch + " in index " + i + " was found in game in index " + j;
                assertFalse(message, cardIsFound);
            }
        }
    }
}