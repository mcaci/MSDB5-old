package game.elements.player;

import game.elements.cardset.Hand;
import game.elements.player.auction.info.AuctionInfo;
import game.elements.player.auction.info.Score;
import game.elements.player.auction.info.Status;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 04/09/15.
 */
@RunWith(Parameterized.class)
public class MockPlayer extends Player {

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
//        performAuctionAction(initialScore);
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getStatus() != Status.FOLDED) {
            assertTrue(auctionInfo.getScore().getScore() > initialScore);
        }
    }

    @Test
    public void testPerformAuctionAction60() throws Exception {
        byte initialScore = 60;
//        performAuctionAction(initialScore);
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getStatus() != Status.FOLDED) {
            assertTrue(auctionInfo.getScore().getScore() > initialScore);
        }
    }

    @Test
    public void testPerformAuctionAction89() throws Exception {
        byte initialScore = 89;
//        performAuctionAction(initialScore);
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getStatus() != Status.FOLDED) {
            assertTrue(auctionInfo.getScore().getScore() > initialScore);
        }
        // TODO: turn the card when side deck is present
    }

    @Test
    public void testPerformAuctionAction119() throws Exception {
        byte initialScore = 119;
//        performAuctionAction(initialScore);
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getStatus() != Status.FOLDED) {
            assertTrue(auctionInfo.getScore().getScore() == 120);
        }
    }

    @Test
    public void testPerformAuctionAction120() throws Exception {
        byte initialScore = 120;
//        performAuctionAction(initialScore);
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getStatus() != Status.FOLDED) {
            assertTrue(auctionInfo.getScore().getScore() == initialScore);
        }
    }

    @Test
    public void testValidity() throws Exception {
        assertNotNull(this.getHand());
        assertFalse(this.getHand().isEmpty());
        assertNotNull(this.getAuctionInfo());
        assertTrue(this.getAuctionInfo().getScore().getScore() >= Score.MIN_SCORE);
        assertTrue(this.getAuctionInfo().getScore().getScore() <= Score.MAX_SCORE);
        assertNotNull(this.getAuctionInfo().getStatus());
    }

    @Override
    public Score chooseNextScore(Hand hand, int currentScore) {
        return null;
    }

    @Override
    public AuctionInfo chooseNextStance(Player playerDeciding, int currentScore) {
        return null;
    }
}
