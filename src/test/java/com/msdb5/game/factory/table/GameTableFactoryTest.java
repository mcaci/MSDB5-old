package com.msdb5.game.factory.table;

import org.junit.After;
import org.junit.Test;

import com.msdb5.game.cardset.Deck;
import com.msdb5.game.player.Player;
import com.msdb5.game.player.mock.MockClassicPlayer;
import com.msdb5.game.player.mock.MockCowardPlayer;
import com.msdb5.game.player.mock.MockUnwaveringPlayer;
import com.msdb5.game.table.GameTable;

import static com.msdb5.game.cardset.Deck.DECK_DEFAULT_SIZE;
import static com.msdb5.game.table.GameTableInfo.NUMBER_OF_PLAYERS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactoryTest {

    private final GameTable mockGameTable;
    private final Player[] mockPlayers = {new MockClassicPlayer(), new MockCowardPlayer(),
    									  new MockClassicPlayer(), new MockUnwaveringPlayer(),
    									  new MockClassicPlayer()};

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
        assertEquals("The size of the deck should be " + DECK_DEFAULT_SIZE, deckSize, DECK_DEFAULT_SIZE);
    }

    public GameTable getMockGameTable() {
        return mockGameTable;
    }
}