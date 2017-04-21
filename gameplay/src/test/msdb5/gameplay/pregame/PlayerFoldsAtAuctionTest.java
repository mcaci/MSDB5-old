package msdb5.gameplay.pregame;

import msdb5.game.player.info.AuctionStatus;
import org.junit.Test;

import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

/**
 * Created by mcaci on 4/21/17.
 */
public class PlayerFoldsAtAuctionTest extends AuctionActionTableTest {

    @Test
    public void testPlayerDoesNotRaiseAuction() throws Exception {
        testScoreAfterPlayerPlaysAuctionRound();
    }

    @Test
    public void testPlayerFolds() throws Exception {
        testPlayerStatus();
    }

    Predicate<AuctionStatus> getStatus() {
        return AuctionStatus::hasFolded;
    }

    int getNewValue() {
        return 100;
    }

    boolean isCondition(Integer auctionValueBeforePlayerMoves, Integer auctionValueAfterPlayerMoves) {
        return Integer.compare(auctionValueAfterPlayerMoves, auctionValueBeforePlayerMoves) == 0;
    }

    IntUnaryOperator getIntUnaryOperator() {
        return auctionValue -> 100;
    }

}
