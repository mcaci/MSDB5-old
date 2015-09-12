package gameplay.start;

import game.elements.Deck;
import game.elements.GameTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 31/08/15.
 */
public class GamePreparatorTest {

    private GamePreparator gamePreparatorTestObject;
    private GameTable gameTable;

    @Before
    public void setUp() throws Exception {
        gamePreparatorTestObject = new GamePreparator();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("GamePreparatorTest: after table creation");
        System.out.println(gameTable.toString());
    }

    @Test
    public void testPrepareGameWithSideDeck() throws Exception {
        gameTable = gamePreparatorTestObject.getPreparedTable(true);
        assertNotNull(gameTable);
        checkDeckSizeAfterPreparation(GameTable.SIDE_DECK_SIZE);
    }

    @Test
    public void testPrepareGameWithoutSideDeck() throws Exception {
        gameTable = gamePreparatorTestObject.getPreparedTable(false);
        assertNotNull(gameTable);
        checkDeckSizeAfterPreparation(GameTable.NO_SIDE_DECK_SIZE);
    }

    private void checkDeckSizeAfterPreparation(int expectedDeckSize) {
        final Deck deck = gameTable.getDeck();
        assertNotNull(deck);
        final int deckSize = deck.getDeck().size();
        assertEquals("At the end of the preparation the game should have " + expectedDeckSize + " cards",
                expectedDeckSize, deckSize);
    }

}