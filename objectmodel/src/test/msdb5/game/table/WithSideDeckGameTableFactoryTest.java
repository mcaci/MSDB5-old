package msdb5.game.table;

import org.junit.Test;

/**
 * Created by nikiforos on 31/08/15.
 */
public class WithSideDeckGameTableFactoryTest extends GameTableFactoryTest {

    public WithSideDeckGameTableFactoryTest() {
        super(true);
    }

    @Override
    @Test
    public void testPlayersHandsSize() throws Exception {
        super.verifyPlayersHandsSize(7);
    }

    @Override
    @Test
    public void testSideDeckIsCreated() throws Exception {
        super.verifySideDeckSize(5);
    }
}