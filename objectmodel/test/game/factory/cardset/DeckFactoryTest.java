package game.factory.cardset;

import game.elements.base.Card;
import game.elements.cardset.Deck;
import org.junit.After;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactoryTest extends CardSetFactoryTest {

    private final Deck mockDeck;

    public DeckFactoryTest() {
        super(new DeckFactory());
        mockDeck = ((DeckFactory) cardSetFactoryTestObject).createDeck();
    }

    @Override
    public Collection<Card> getCardSet() {
        return mockDeck.getCardSet();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("DeckFactoryTest: showing deck after creation");
        System.out.println(mockDeck.toString());
    }

    @Test
    public void testGetCreatedDeck() throws Exception {
        assertNotNull(mockDeck);
        assertNotNull(mockDeck.getCardSet());
        assertEquals("Size doesn't correspond to test value " + Deck.DECK_DEFAULT_SIZE, Deck.DECK_DEFAULT_SIZE,
                mockDeck.getCardSet().size());
    }

    public Deck getMockDeck() {
        return mockDeck;
    }

}
