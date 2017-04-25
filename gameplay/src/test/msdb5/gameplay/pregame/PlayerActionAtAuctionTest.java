package msdb5.gameplay.pregame;

import msdb5.game.player.Player;
import msdb5.game.player.info.AuctionStatus;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static org.junit.Assert.assertTrue;

/**
 * Created by mcaci on 4/21/17.
 */
public abstract class PlayerActionAtAuctionTest {

    private final IntSupplier auctionScoreSupplier;

    private Player player;
    private AtomicInteger auctionValue;

    PlayerActionAtAuctionTest(IntSupplier auctionScoreSupplier) {
        this.auctionScoreSupplier = auctionScoreSupplier;
    }

    @Before
    public void setUp() throws Exception {
        player = new TestPlayerForGamePlayer(1);
        auctionValue = new AtomicInteger(60);
        setAuctionInitialValue(auctionScoreSupplier);
    }

    private void setAuctionInitialValue(IntSupplier auctionScoreSupplier) {
        this.auctionValue.set(auctionScoreSupplier.getAsInt());
    }

    @Test
    public void auctionScoreNeverGoesOverMax() throws Exception {
        Player player = this.player;
        player.actsOnAuction(this.auctionValue, player.getFoldingDecision(), player.getChooseNextScoreFunction());
        System.out.println(this.auctionValue.get());
        assertTrue("Actual value of auction is more than 120: " + this.auctionValue.get(), this.auctionValue.get() <= AuctionRoulette.AUCTION_MAX);
    }

    @Test
    public void testPlayersScoreAfterAuction() {
        this.auctionValue = this.player.actsOnAuction(this.auctionValue, this.player.getFoldingDecision(), this.player.getChooseNextScoreFunction());
        int playersScoreAfterAuction = this.player.tellAuctionScore();
        assertTrue("Auction value after decision: " + this.auctionValue + " - Player's score: " + playersScoreAfterAuction,
                playersScoreAfterAuction <= this.auctionValue.get());
    }

    void testScoreAfterPlayerPlaysAuctionRound(BiPredicate<Integer, Integer> scoresPredicate) {
        int auctionValueBeforePlayerMoves = this.auctionValue.get();
        this.auctionValue = this.player.actsOnAuction(this.auctionValue, this.player.getFoldingDecision(), this.player.getChooseNextScoreFunction());
        assertTrue("Auction value before: " + auctionValueBeforePlayerMoves + " - Auction value after: " +this.auctionValue
                , scoresPredicate.test(auctionValueBeforePlayerMoves, this.auctionValue.get()));
    }

    void testPlayerStatus(Predicate<AuctionStatus> statusPredicate) {
        Player player = this.player;
        player.actsOnAuction(this.auctionValue, player.getFoldingDecision(), player.getChooseNextScoreFunction());
        assertTrue("Player is in state: " + player.getAuctionInfo().getAuctionStatus(),
                player.getAuctionStatusFor(statusPredicate));
    }
}
