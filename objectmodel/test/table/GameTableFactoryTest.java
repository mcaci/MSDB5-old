package table;

import deck.Deck;
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

    @Test
    public void testGetCreatedTable() throws Exception {
        assertNotNull(testGameTable);

        Deck tableDeck = testGameTable.getDeck();
        assertNotNull(tableDeck);

        Player[] players = testGameTable.getPlayers();
        assertNotNull(players);
        int numberOfPlayers = players.length;
        assertEquals("Number of players should be " + GameTableFactory.NUMBER_OF_PLAYERS, numberOfPlayers, GameTableFactory
                .NUMBER_OF_PLAYERS);
    }
}