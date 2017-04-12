package msdb5.game.player;

import msdb5.game.card.Card;
import msdb5.game.card.MockCard;
import msdb5.game.card.set.Deck;
import msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import msdb5.game.player.info.AuctionInfo;
import msdb5.game.player.info.AuctionStatus;
import org.junit.After;
import org.junit.Test;

import java.util.function.IntUnaryOperator;

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
        if (currentScore < Player.MIN_AUCTION_SCORE || currentScore >= Player.MAX_AUCTION_SCORE) {
            throw new AuctionOnScoreOutOfBoundsException();
        }
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (!this.getAuctionStatusFor(AuctionStatus::hasFolded)) {
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

    private int chooseNextScore(int currentScore) {
        return decideNextScore(currentScore, (score) -> {
            int scoreToCompute = score + scoreIncrement;
            scoreToCompute = Math.max(scoreToCompute, Player.MIN_AUCTION_SCORE);
            scoreToCompute = Math.min(scoreToCompute, Player.MAX_AUCTION_SCORE);
            return scoreToCompute;
        });
    }

    protected int decideNextScore(int currentScore, IntUnaryOperator operation){
        return operation.applyAsInt(currentScore);
    }

    @Override
    public void swapCardsWithSideDeck(Deck deck) {
        return; // TODO: mock implementation
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
        assertTrue(ScoreWithinBoundsTest.howIsScoreWithRespectToBounds(this.tellAuctionScore(), ScoreWithinBoundsTest.sameOrGreaterThanMin));
        assertTrue(ScoreWithinBoundsTest.howIsScoreWithRespectToBounds(this.tellAuctionScore(), ScoreWithinBoundsTest.sameOrLowerThanMax));
        assertNotNull(this.getAuctionInfo().getAuctionStatus());
        assertFalse(this.getAuctionStatusFor(AuctionStatus::actionWasDone));
        assertFalse(this.getAuctionStatusFor(AuctionStatus::isWinner));
    }

    @Test
    public void testSetAsWinner() throws Exception {
        this.setAuctionStatusAs(() -> AuctionStatus.AUCTION_WINNER);
    }

}
