package game.elements.player.mock;

import game.elements.player.Player;
import game.elements.player.info.AuctionInfo;
import game.elements.player.info.AuctionScore;
import game.elements.player.info.AuctionStatus;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 01/01/16.
 */
public abstract class MockPlayer extends Player {

    private final static byte[] SCORES_TO_TEST = {0, 60, 89, 119, 120};
    private final float chanceToFold;
    private final int scoreIncrement;

    public MockPlayer(float chanceToFold, int scoreIncrement) {
        this.chanceToFold = chanceToFold;
        this.scoreIncrement = scoreIncrement;
    }

    @Override
    public AuctionInfo performAuctionAction(int currentScore) {
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (!auctionInfo.getAuctionStatus().hasFolded()) {
            if (decideToContinueAuction()) {
                auctionInfo.setAuctionStatus(AuctionStatus.IN_AUCTION);
                auctionInfo.setAuctionScore(chooseNextScore(currentScore));
            } else {
                auctionInfo.setAuctionStatus(AuctionStatus.FOLDED);
            }
        }
        return auctionInfo;
    }

    private AuctionScore chooseNextScore(int currentScore) {
        final AuctionScore auctionScore = new AuctionScore();
        final int nextScore = decideNextScore(currentScore);
        auctionScore.setSafeScore(nextScore);
        return auctionScore;
    }

    private int decideNextScore(int currentScore) {
        int nextScore = currentScore + scoreIncrement;
        nextScore = Math.max(nextScore, AuctionScore.MIN_SCORE);
        nextScore = Math.min(nextScore, AuctionScore.MAX_SCORE);
        return nextScore;
    }

    private boolean decideToContinueAuction() {
        final double tokenToStay = Math.random();
        return tokenToStay >= this.chanceToFold;
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

    @Test
    public void testPerformAuctionAction89() throws Exception {
        byte initialScore = 89;
        chosenScoreCheck(initialScore);
        // TODO: turn the card when side deck is present
    }

    private void chosenScoreCheck(byte initialScore) {
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        final byte score = auctionInfo.getAuctionScore().getScore();
        String message = "Comparing actual score of " + score + " with the initial score of " + initialScore;
        if (auctionInfo.getAuctionStatus() != AuctionStatus.FOLDED || initialScore < 120) {
            assertTrue(message, score > initialScore);
        } else {
            assertTrue(message, score == initialScore);
        }
    }
}
