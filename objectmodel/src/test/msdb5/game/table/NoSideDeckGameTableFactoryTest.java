package msdb5.game.table;

import org.junit.Test;

/**
 * Created by nikiforos on 31/08/15.
 */
public class NoSideDeckGameTableFactoryTest extends GameTableFactoryTest {

    public NoSideDeckGameTableFactoryTest() {
        super(false);
    }

    @Override
    @Test
    public void testPlayersHandsSize() throws Exception {
        super.verifyPlayersHandsSize(8);
    }

    @Override
    @Test
    public void testSideDeckIsCreated() throws Exception {
        super.verifySideDeckSize(0);
    }
}