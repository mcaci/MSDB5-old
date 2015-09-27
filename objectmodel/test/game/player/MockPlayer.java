package game.player;

import game.elements.cardset.MockHand;
import game.player.auction.AuctionStance;
import game.player.auction.Score;
import game.player.auction.Status;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import strategy.auction.MockAuctionAction;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 04/09/15.
 */
@RunWith(Parameterized.class)
public class MockPlayer extends Player {

    public MockPlayer(boolean isSideDeckPresent) {
        this(isSideDeckPresent, new MockAuctionAction());
    }

    MockPlayer(boolean isSideDeckPresent, MockAuctionAction auctionAction) {
        super(new MockHand(isSideDeckPresent), auctionAction);
    }

    @Parameterized.Parameters
    public static Collection initParameters() {
        return Arrays.asList(new Object[][]{
                {true},
                {false}
        });
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("MockPlayer: " + this);
    }

    @Test
    public void testPerformAuctionAction0() throws Exception {
        byte initialScore = 0;
        performAuctionAction(initialScore);
        final AuctionStance auctionStance = this.getAuctionStance();
        if (auctionStance.getStatus() != Status.FOLDED) {
            assertTrue(auctionStance.getScore().getScore() > initialScore);
        }
    }

    @Test
    public void testPerformAuctionAction60() throws Exception {
        byte initialScore = 60;
        performAuctionAction(initialScore);
        final AuctionStance auctionStance = this.getAuctionStance();
        if (auctionStance.getStatus() != Status.FOLDED) {
            assertTrue(auctionStance.getScore().getScore() > initialScore);
        }
    }

    @Test
    public void testPerformAuctionAction89() throws Exception {
        byte initialScore = 89;
        performAuctionAction(initialScore);
        final AuctionStance auctionStance = this.getAuctionStance();
        if (auctionStance.getStatus() != Status.FOLDED) {
            assertTrue(auctionStance.getScore().getScore() > initialScore);
        }
        // TODO: turn the card when side deck is present
    }

    @Test
    public void testPerformAuctionAction119() throws Exception {
        byte initialScore = 119;
        performAuctionAction(initialScore);
        final AuctionStance auctionStance = this.getAuctionStance();
        if (auctionStance.getStatus() != Status.FOLDED) {
            assertTrue(auctionStance.getScore().getScore() == 120);
        }
    }

    @Test
    public void testPerformAuctionAction120() throws Exception {
        byte initialScore = 120;
        performAuctionAction(initialScore);
        final AuctionStance auctionStance = this.getAuctionStance();
        if (auctionStance.getStatus() != Status.FOLDED) {
            assertTrue(auctionStance.getScore().getScore() == initialScore);
        }
    }

    @Test
    public void testValidity() throws Exception {
        assertNotNull(this.getHand());
        assertFalse(this.getHand().isEmpty());
        assertNotNull(this.getAuctionStance());
        assertTrue(this.getAuctionStance().getScore().getScore() >= Score.MIN_SCORE);
        assertTrue(this.getAuctionStance().getScore().getScore() <= Score.MAX_SCORE);
        assertNotNull(this.getAuctionStance().getStatus());
    }

}
