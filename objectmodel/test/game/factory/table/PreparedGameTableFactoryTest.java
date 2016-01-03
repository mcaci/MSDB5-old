package game.factory.table;

import game.table.GameTableInfo;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

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