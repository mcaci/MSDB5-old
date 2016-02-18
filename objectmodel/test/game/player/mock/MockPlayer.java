package game.player.mock;

import game.cardset.Deck;
import game.cardset.card.Card;
import game.cardset.card.MockCard;
import game.player.Player;
import game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import game.player.info.AuctionInfo;
import game.player.info.AuctionScore;
import game.player.info.AuctionStatus;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 01/01/16.
 */
public abstract class MockPlayer extends Player {

    private final float chanceToFold;
    private final int scoreIncrement;

    public MockPlayer(float chanceToFold, int scoreIncrement) {
        this.chanceToFold = chanceToFold;
        this.scoreIncrement = scoreIncrement;
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Player tested: " + this);
    }

    @Override
    public AuctionInfo performAuctionAction(int currentScore) throws AuctionOnScoreOutOfBoundsException {
        if (currentScore < AuctionScore.MIN_SCORE || currentScore >= AuctionScore.MAX_SCORE) {
            throw new AuctionOnScoreOutOfBoundsException();
        }
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (!hasFolded()) {
            if (decideToContinueAuction()) {
                auctionInfo.setAuctionStatus(AuctionStatus.IN_AUCTION);
                auctionInfo.setAuctionScore(chooseNextScore(currentScore));
            } else {
                auctionInfo.setAuctionStatus(AuctionStatus.FOLDED);
            }
        }
        return auctionInfo;
    }

    @Override
    public Card chooseCompanionCard() {
        return new MockCard();
    }

    private AuctionScore chooseNextScore(int currentScore) {
        final AuctionScore auctionScore = new AuctionScore();
        final int nextScore = decideNextScore(currentScore);
        auctionScore.setSafeScore(nextScore);
        return auctionScore;
    }

    @Override
    public void swapCardsWithSideDeck(Deck deck) {
        return; // TODO: mock implementation
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
        assertTrue(this.getHand().isEmpty()); // Hand is empty at player creation
        assertNotNull(this.getAuctionInfo());
        assertTrue(this.tellAuctionScore() >= AuctionScore.MIN_SCORE);
        assertTrue(this.tellAuctionScore() <= AuctionScore.MAX_SCORE);
        assertNotNull(this.getAuctionInfo().getAuctionStatus());
        assertFalse(this.hasActed());
    }

}
