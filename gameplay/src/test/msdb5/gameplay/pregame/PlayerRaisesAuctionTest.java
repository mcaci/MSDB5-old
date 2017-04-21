package msdb5.gameplay.pregame;

import msdb5.game.player.info.AuctionStatus;
import org.junit.Test;

import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

/**
 * Created by mcaci on 4/21/17.
 */
public class PlayerRaisesAuctionTest extends AuctionActionTableTest {

    @Test
    public void testPlayerRaisesAuction() throws Exception {
        testScoreAfterPlayerPlaysAuctionRound();
    }

    @Test
    public void testPlayerIsActiveAfterAuction() throws Exception {
        testPlayerStatus();
    }

    Predicate<AuctionStatus> getStatus() {
        return AuctionStatus::isInAuction;
    }

    int getNewValue() {
        return 70;
    }

    IntUnaryOperator getIntUnaryOperator() {
        return auctionValue -> auctionValue;
    }

    @Override
    boolean isCondition(Integer auctionValueBeforePlayerMoves, Integer auctionValueAfterPlayerMoves) {
        return Integer.compare(auctionValueAfterPlayerMoves, auctionValueBeforePlayerMoves) > 0;
    }
}
