package msdb5.game.table;

import msdb5.game.card.set.SideDeck;
import msdb5.game.player.MockClassicPlayer;
import msdb5.game.player.MockCowardPlayer;
import msdb5.game.player.MockUnwaveringPlayer;
import msdb5.game.player.Player;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static msdb5.game.table.GameTableInfo.NUMBER_OF_PLAYERS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 31/08/15.
 */
@RunWith(Parameterized.class)
public class GameTableFactoryTest {

    private final boolean isSideDeckUsed;
    private final GameTable mockGameTable;
    private final Player[] mockPlayers = {new MockClassicPlayer(), new MockCowardPlayer(), new MockClassicPlayer(), new MockUnwaveringPlayer(), new MockClassicPlayer()};

    public GameTableFactoryTest(boolean isSideDeckUsed) {
        mockGameTable = new GameTableFactory(isSideDeckUsed).create(mockPlayers);
        this.isSideDeckUsed = isSideDeckUsed;
    }

    @Parameterized.Parameters
    public static Collection initParameters() {
        return Arrays.asList(new Object[][]{
                {true},
                {false}
        });
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("GameTableFactoryTest: showing table after creation");
        System.out.println(mockGameTable.toString());
    }

    @Test
    public void testPlayersAreCreated() throws Exception {
        assertEquals("Number of players should be " + NUMBER_OF_PLAYERS, mockGameTable.getPlayers().length, NUMBER_OF_PLAYERS);
        Player[] players = this.mockGameTable.getPlayers();
        Stream.of(players).
                mapToInt(player -> player.getHand().size()).
                forEach(handSize -> assertTrue("Hand should be between 7 and 8 cards and instead is of " + handSize, handSize == 7 || handSize == 8));
    }

    @Test
    public void testSideDeckIsCreated() throws Exception {
        SideDeck sideDeck = this.mockGameTable.getSideDeck();
        int deckSize = this.isSideDeckUsed ? GameTableInfo.SIDE_DECK_SIZE : GameTableInfo.NO_SIDE_DECK_SIZE;
        assertEquals("The size of the deck should be " + deckSize, sideDeck.size(), deckSize);
    }
}