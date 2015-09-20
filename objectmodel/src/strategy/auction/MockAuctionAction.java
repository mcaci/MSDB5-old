package strategy.auction;

import game.elements.cardset.Hand;
import game.player.Player;
import game.player.auction.AuctionStance;
import game.player.auction.Score;
import game.player.auction.Status;

/**
 * Created by nikiforos on 14/09/15.
 */
// TODO: move to test package and use real implementation in src, the deprecated is to make it explicit
@Deprecated
public class MockAuctionAction implements IAuctionAction {
    @Override
    public Score chooseNextScore(Hand hand, int currentScore) {
        final Score score = new Score();
        final int nextScore = decideNextScore(currentScore);
        score.setSafeScore(nextScore);
        return score;
    }

    private int decideNextScore(int currentScore) {
        int nextScore = ++currentScore;
        nextScore = Math.max(nextScore, MIN_AUCTION_SCORE);
        nextScore = Math.min(nextScore, MAX_AUCTION_SCORE);
        return nextScore;
    }

    @Override
    public AuctionStance chooseNextStance(Player playerDeciding, int currentScore) {
        final AuctionStance auctionStance = playerDeciding.getAuctionStance();
        if (!auctionStance.getStatus().hasFolded()) {
            final double randomFlag = Math.random();
            if (randomFlag > 0.5) {
                auctionStance.setStatus(Status.IN_AUCTION);
                auctionStance.setScore(chooseNextScore(playerDeciding.getHand(), currentScore));
            } else {
                auctionStance.setStatus(Status.FOLDED);
            }
        }
        return auctionStance;
    }
}
