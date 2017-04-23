package msdb5.gameplay.pregame;

import msdb5.game.player.info.AuctionStatus;
import org.junit.Test;

/**
 * Created by mcaci on 4/21/17.
 */
public class PlayerFoldsAtAuctionTest extends PlayerActionAtAuctionTest {

    public PlayerFoldsAtAuctionTest() {
        super(() -> 100);
    }

    @Test
    public void testPlayerDoesNotRaiseAuctionScore() throws Exception {
        testScoreAfterPlayerPlaysAuctionRound(Integer::equals);
    }

    @Test
    public void testPlayerFolds() throws Exception {
        testPlayerStatus(AuctionStatus::hasFolded);
    }

}
