package com.msdb5.game.factory.cardset;

import com.msdb5.game.cardset.Deck;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactoryTest extends CardSetFactoryTest {

    public DeckFactoryTest() {
        super(new DeckFactory());
        this.mockCardSet = ((DeckFactory) this.cardSetFactoryTestObject).createDeck();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("DeckFactoryTest: showing deck after creation");
        super.tearDown();
    }

    @Override
    public void testOnConcreteSize(int deckSize) {
        assertEquals("Size doesn't correspond to test value " + Deck.DEFAULT_DECK_SIZE, Deck.DEFAULT_DECK_SIZE,
                deckSize);
    }

    public Deck getMockDeck() {
        return (Deck) mockCardSet;
    }

}
