package msdb5.gameplay.pregame;

import msdb5.game.player.Player;
import msdb5.game.player.info.AuctionStatus;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mcaci on 4/21/17.
 */
public abstract class AuctionActionTableTest {

    private final IntSupplier auctionScoreSupplier;

    private AtomicAuctionTable table;

    AuctionActionTableTest(IntSupplier auctionScoreSupplier) {
        this.auctionScoreSupplier = auctionScoreSupplier;
    }

    @Before
    public void setUp() throws Exception {
        table = new AtomicAuctionTable(new AtomicInteger(AtomicAuctionTable.AUCTION_BASE),
                new TestPlayerForGamePlayer(1), new TestPlayerForGamePlayer(2),
                new TestPlayerForGamePlayer(3), new TestPlayerForGamePlayer(4),
                new TestPlayerForGamePlayer(5));
        setAuctionInitialValue(auctionScoreSupplier);
    }

    private void setAuctionInitialValue(IntSupplier auctionScoreSupplier) {
        table.getAuctionValue().set(auctionScoreSupplier.getAsInt());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("auctionActionTable after test is run");
        System.out.println(table);
    }

    @Test
    public void auctionScoreNeverGoesOverMax() throws Exception {
        Player player = table.getPlayers()[0];
        player.actsOnAuction(table.getAuctionValue(), player.getFoldingDecision(), player.getChooseNextScoreFunction());
        System.out.println(table.getAuctionValue().get());
        assertTrue("Actual value of auction is more than 120: " + table.getAuctionValue().get(), table.getAuctionValue().get() <= AtomicAuctionTable.AUCTION_MAX);
    }

    @Test
    public void testExecuteAuction() throws Exception {
        table.executeAuctionRound();
        assertTrue(true);
    }

    @Test
    public void testPlayersScoreAfterAuction() {
        int auctionValueAfterPlayerMoves = table.getPlayers()[0].actsOnAuction(table.getAuctionValue(), table.getPlayers()[0].getFoldingDecision(), table.getPlayers()[0].getChooseNextScoreFunction());
        int playersScoreAfterAuction = table.getPlayers()[0].tellAuctionScore();
        assertEquals("Auction value after decision: " + auctionValueAfterPlayerMoves + " - Player's score: " + playersScoreAfterAuction
                , playersScoreAfterAuction, auctionValueAfterPlayerMoves);
    }

    void testScoreAfterPlayerPlaysAuctionRound(BiPredicate<Integer, Integer> scoresPredicate) {
        int auctionValueBeforePlayerMoves = table.getAuctionValue().get();
        int auctionValueAfterPlayerMoves = table.getPlayers()[0].actsOnAuction(table.getAuctionValue(), table.getPlayers()[0].getFoldingDecision(), table.getPlayers()[0].getChooseNextScoreFunction());
        assertTrue("Auction value before: " + auctionValueBeforePlayerMoves + " - Auction value after: " +auctionValueAfterPlayerMoves
                , scoresPredicate.test(auctionValueBeforePlayerMoves, auctionValueAfterPlayerMoves));
    }

    void testPlayerStatus(Predicate<AuctionStatus> statusPredicate) {
        Player player = table.getPlayers()[0];
        player.actsOnAuction(table.getAuctionValue(), player.getFoldingDecision(), player.getChooseNextScoreFunction());
        assertTrue("Player is in state: " + player.getAuctionInfo().getAuctionStatus(),
                player.getAuctionStatusFor(statusPredicate));
    }
}
