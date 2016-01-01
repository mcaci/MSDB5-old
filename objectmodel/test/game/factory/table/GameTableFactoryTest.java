package game.factory.table;

import game.cardset.Deck;
import game.player.Player;
import game.table.GameTable;
import org.junit.After;
import org.junit.Test;

import static game.cardset.Deck.DECK_DEFAULT_SIZE;
import static game.table.GameTable.NUMBER_OF_PLAYERS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactoryTest {

    private final GameTable mockGameTable;

    public GameTableFactoryTest() {
        this(new GameTableFactory());
    }

    GameTableFactoryTest(GameTableFactory gameTableFactoryTestObject) {
        mockGameTable = gameTableFactoryTestObject.create();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("GameTableFactoryTest: showing table after creation");
        System.out.println(mockGameTable.toString());
    }

    @Test
    public void testCreation() throws Exception {
        assertNotNull(mockGameTable);
    }

    @Test
    public void testOnDeck() throws Exception {
        // test for the created game
        Deck tableDeck = mockGameTable.getDeck();
        assertNotNull(tableDeck);
        testOnDeckSize(tableDeck.getCardSet().size());
    }

    @Test
    public void testOnPlayers() throws Exception {
        // test for the created players
        Player[] players = mockGameTable.getPlayers();
        assertNotNull(players);
        int numberOfPlayers = players.length;
        assertEquals("Number of players should be " + NUMBER_OF_PLAYERS, numberOfPlayers, NUMBER_OF_PLAYERS);
    }

    void testOnDeckSize(int deckSize) {
        assertEquals("The size of the deck should be " + DECK_DEFAULT_SIZE, deckSize, DECK_DEFAULT_SIZE);
    }

    public GameTable getMockGameTable() {
        return mockGameTable;
    }
}