package msdb5.gameplay.pregame;

import msdb5.game.player.Player;
import msdb5.game.player.info.AuctionStatus;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import static org.junit.Assert.assertTrue;

/**
 * Created by mcaci on 4/21/17.
 */
public abstract class AuctionActionTableTest {

    private AtomicAuctionTable table;

    abstract boolean isCondition(Integer auctionValueBeforePlayerMoves, Integer auctionValueAfterPlayerMoves);
    abstract IntUnaryOperator getIntUnaryOperator();
    abstract Predicate<AuctionStatus> getStatus();
    abstract int getNewValue();

    @Before
    public void setUp() throws Exception {
        table = new AtomicAuctionTable(new AtomicInteger(AtomicAuctionTable.AUCTION_BASE), new TestPlayerForGamePlayer());
    }

    @Test
    public void auctionScoreNeverGoesOverMax() throws Exception {
        Player player = table.getPlayers()[0];
        player.actsOnAuction(table.getAuctionValue());
        assertTrue("Actual value of auction is more than 120: " + table.getAuctionValue().get(),table.getAuctionValue().get() <= AtomicAuctionTable.AUCTION_MAX);
    }

    void testScoreAfterPlayerPlaysAuctionRound() {
        int auctionValueBeforePlayerMoves = table.getAuctionValue().updateAndGet(getIntUnaryOperator());
        int auctionValueAfterPlayerMoves = table.getPlayers()[0].actsOnAuction(table.getAuctionValue());
        assertTrue("Auction value before: " + auctionValueBeforePlayerMoves + " - Auction value after: " +auctionValueAfterPlayerMoves
                , isCondition(auctionValueBeforePlayerMoves, auctionValueAfterPlayerMoves));
    }

    void testPlayerStatus() {
        table.getAuctionValue().set(getNewValue());
        Player player = table.getPlayers()[0];
        player.actsOnAuction(table.getAuctionValue());
        assertTrue("Player is in state: " + player.getAuctionInfo().getAuctionStatus(),
                player.getAuctionStatusFor(getStatus()));
    }
}
