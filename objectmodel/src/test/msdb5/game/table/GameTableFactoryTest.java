package msdb5.game.table;

import msdb5.game.card.set.Deck;
import msdb5.game.player.Player;
import msdb5.game.player.MockClassicPlayer;
import msdb5.game.player.MockCowardPlayer;
import msdb5.game.player.MockUnwaveringPlayer;
import org.junit.After;
import org.junit.Test;

import static msdb5.game.card.set.Deck.DEFAULT_DECK_SIZE;
import static msdb5.game.table.GameTableInfo.NUMBER_OF_PLAYERS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactoryTest {

    private final GameTable mockGameTable;
    private final Player[] mockPlayers = {new MockClassicPlayer(), new MockCowardPlayer(), new MockClassicPlayer(), new MockUnwaveringPlayer(), new MockClassicPlayer()};

    public GameTableFactoryTest() {
        this(new GameTableFactory());
    }

    GameTableFactoryTest(GameTableFactory gameTableFactoryTestObject) {
        mockGameTable = gameTableFactoryTestObject.create(mockPlayers);
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
        assertEquals("The size of the deck should be " + DEFAULT_DECK_SIZE, deckSize, DEFAULT_DECK_SIZE);
    }

    public GameTable getMockGameTable() {
        return mockGameTable;
    }
}