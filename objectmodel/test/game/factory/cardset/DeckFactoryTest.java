package game.factory.cardset;

import game.elements.base.Card;
import game.elements.cardset.Deck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactoryTest extends CardSetFactoryTest {

    private Deck testObjectDeck;

    @Override
    public Collection<Card> getCardSet() {
        return testObjectDeck.getCardSet();
    }

    @Before
    public void setUp() throws Exception {
        cardSetFactoryTestObject = new DeckFactory();
        testObjectDeck = ((DeckFactory) cardSetFactoryTestObject).createDeck();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("DeckFactoryTest: showing deck after creation");
        System.out.println(testObjectDeck.toString());
    }

    @Test
    public void testGetCreatedDeck() throws Exception {
        assertNotNull(testObjectDeck);
        assertNotNull(testObjectDeck.getCardSet());
        assertEquals("Size doesn't correspond to test value " + Deck.DECK_DEFAULT_SIZE, Deck.DECK_DEFAULT_SIZE,
                testObjectDeck.getCardSet().size());
    }

}
