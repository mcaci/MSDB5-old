package game.elements.player.strategy.auction;

import game.elements.cardset.DeckAwareHand;
import game.elements.player.Player;
import game.elements.player.auction.AuctionInfo;
import game.elements.player.auction.Score;
import game.elements.player.auction.Status;

/**
 * Created by nikiforos on 14/09/15.
 */
// TODO: move to test package and use real implementation in src, the deprecated is to make it explicit
@Deprecated
public class MockAuctionAction implements IAuctionAction {

    private static final double CHANCE_TO_FOLD = 0.4;

    @Override
    public Score chooseNextScore(DeckAwareHand deckAwareHand, int currentScore) {
        final Score score = new Score();
        final int nextScore = decideNextScore(currentScore);
        score.setSafeScore(nextScore);
        return score;
    }

    int decideNextScore(int currentScore) {
        int nextScore = ++currentScore;
        nextScore = Math.max(nextScore, MIN_AUCTION_SCORE);
        nextScore = Math.min(nextScore, MAX_AUCTION_SCORE);
        return nextScore;
    }

    @Override
    public AuctionInfo chooseNextStance(Player playerDeciding, int currentScore) {
        final AuctionInfo auctionInfo = playerDeciding.getAuctionInfo();
        if (!auctionInfo.getStatus().hasFolded()) {
            final double randomFlag = Math.random();
            if (randomFlag > CHANCE_TO_FOLD) {
                auctionInfo.setStatus(Status.IN_AUCTION);
                auctionInfo.setScore(chooseNextScore(playerDeciding.getDeckAwareHand(), currentScore));
            } else {
                auctionInfo.setStatus(Status.FOLDED);
            }
        }
        return auctionInfo;
    }
}
