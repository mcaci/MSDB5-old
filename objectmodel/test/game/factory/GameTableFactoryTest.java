package game.factory;

import game.elements.Deck;
import game.elements.GameTable;
import game.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static game.elements.Deck.DEFAULT_SIZE;
import static game.elements.GameTable.NUMBER_OF_PLAYERS;
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

        // test for the created game
        Deck tableDeck = testGameTable.getDeck();
        assertNotNull(tableDeck);
        final int deckSize = tableDeck.getDeck().size();
        assertEquals("The size of the game should be " + DEFAULT_SIZE, deckSize, DEFAULT_SIZE);

        // test for the created players
        Player[] players = testGameTable.getPlayers();
        assertNotNull(players);
        int numberOfPlayers = players.length;
        assertEquals("Number of players should be " + NUMBER_OF_PLAYERS, numberOfPlayers, NUMBER_OF_PLAYERS);
    }
}