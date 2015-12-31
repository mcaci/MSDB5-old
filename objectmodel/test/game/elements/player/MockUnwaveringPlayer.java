package game.elements.player;

import game.elements.player.auction.info.AuctionInfo;
import game.elements.player.auction.info.AuctionScore;
import game.elements.player.auction.info.AuctionStatus;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockUnwaveringPlayer extends MockPlayer {

    private static final int SCORE_INCREMENT = 7;

    @After
    public void tearDown() throws Exception {
        System.out.println("MockPlayer: " + this);
    }

    @Test
    public void testValidity() throws Exception {
        assertNotNull(this.getHand());
        assertFalse(this.getHand().isEmpty());
        assertNotNull(this.getAuctionInfo());
        assertTrue(this.getAuctionInfo().getAuctionScore().getScore() >= AuctionScore.MIN_SCORE);
        assertTrue(this.getAuctionInfo().getAuctionScore().getScore() <= AuctionScore.MAX_SCORE);
        assertNotNull(this.getAuctionInfo().getAuctionStatus());
    }

    @Override
    public AuctionInfo performAuctionAction(int currentScore) {
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (!auctionInfo.getAuctionStatus().hasFolded()) {
            auctionInfo.setAuctionStatus(AuctionStatus.IN_AUCTION);
            auctionInfo.setAuctionScore(chooseNextScore(this.getHand(), currentScore));
        }
        return auctionInfo;
    }

    @Override
    int decideNextScore(int currentScore) {
        return currentScore + SCORE_INCREMENT;
    }

}
