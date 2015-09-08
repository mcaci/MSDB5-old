package game.factory;

import game.elements.Card;
import game.elements.Deck;
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
        return testObjectDeck.getDeck();
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
        assertNotNull(testObjectDeck.getDeck());
        assertEquals("Size doesn't correspond to test value " + Deck.DEFAULT_SIZE, Deck.DEFAULT_SIZE,
                testObjectDeck.getDeck().size());
    }

}
