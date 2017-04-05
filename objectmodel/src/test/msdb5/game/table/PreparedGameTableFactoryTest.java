package msdb5.game.table;

import msdb5.game.player.Player;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 31/08/15.
 */
@RunWith(Parameterized.class)
public class PreparedGameTableFactoryTest extends GameTableFactoryTest {

    private final boolean isSideDeckUsed;

    public PreparedGameTableFactoryTest(boolean isSideDeckUsed) {
        super(new PreparedGameTableFactory(isSideDeckUsed));
        this.isSideDeckUsed = isSideDeckUsed;
    }

    @Parameterized.Parameters
    public static Collection initParameters() {
        return Arrays.asList(new Object[][]{
                {true},
                {false}
        });
    }

    @Override
    public void testOnPlayers() throws Exception {
        super.testOnPlayers();
        Player[] players = getMockGameTable().getPlayers();
        for (Player player :
                players) {
            int handSize = player.getHand().size();
            assertTrue("Hand should be between 7 and 8 cards and instead is of " + handSize, handSize == 7 || handSize == 8);
        }
    }

    @Override
    void testOnDeckSize(int deckSize) {
        // test for the created game
        int sideDeckSize = GameTableInfo.NO_SIDE_DECK_SIZE;
        if (isSideDeckUsed) {
            sideDeckSize = GameTableInfo.SIDE_DECK_SIZE;
        }
        assertEquals("At the end of the preparation the deck should have " + sideDeckSize + " cards",
                sideDeckSize, deckSize);
    }

}