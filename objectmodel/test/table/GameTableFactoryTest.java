package table;

import deck.Deck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import player.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactoryTest {

    private GameTableFactory testGameTableFactory;
    private GameTable testGameTable;

    @Before
    public void setUp() throws Exception {
        testGameTableFactory = new GameTableFactory();
        testGameTable = testGameTableFactory.getCreatedGameTable();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("GameTableFactoryTest: showing table after creation");
        System.out.println(testGameTable.toString());
    }

    @Test
    public void testGetCreatedTable() throws Exception {
        assertNotNull(testGameTable);

        Deck tableDeck = testGameTable.getDeck();
        assertNotNull(tableDeck);
        final int deckSize = tableDeck.getSize();
        assertEquals("The size of the deck should be " + GameTableFactory.MAX_DECK_SIZE, deckSize, GameTableFactory
                .MAX_DECK_SIZE);

        Player[] players = testGameTable.getPlayers();
        assertNotNull(players);
        int numberOfPlayers = players.length;
        assertEquals("Number of players should be " + GameTableFactory.NUMBER_OF_PLAYERS, numberOfPlayers, GameTableFactory
                .NUMBER_OF_PLAYERS);
    }
}