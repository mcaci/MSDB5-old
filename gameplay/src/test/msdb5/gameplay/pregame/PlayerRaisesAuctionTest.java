package msdb5.gameplay.pregame;

import org.junit.Test;

import static msdb5.game.player.info.AuctionStatus.IN_AUCTION;

/**
 * Created by mcaci on 4/21/17.
 */
public class PlayerRaisesAuctionTest extends PlayerActionAtAuctionTest {

    public PlayerRaisesAuctionTest() {
        super(() -> 80);
    }

    @Test
    public void testPlayerRaisesAuctionScore() throws Exception {
        testScoreAfterPlayerPlaysAuctionRound((before, after) -> Integer.compare(before, after) < 0);
    }

    @Test
    public void testPlayerIsActiveAfterAuction() throws Exception {
        testPlayerStatus(status -> status == IN_AUCTION);
    }
}
