package msdb5.gameplay.ingame;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by nikiforos on 22/05/17.
 */
public class AfterIngameNonWinnerRoundTest extends AfterIngameRoundTest {

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        roundTest = new EveryoneHasPlayedACardTest(this.gameTable.getPlayers()[0]);
    }

    @Test
    public void testPlayersHandsAreOneCardShort() throws Exception {
        execute(player -> player.getHand());
    }
}
