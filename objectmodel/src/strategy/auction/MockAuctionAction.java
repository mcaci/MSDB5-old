package strategy.auction;

import game.player.Hand;
import game.player.Player;
import game.player.auction.AuctionStance;
import game.player.auction.Score;
import game.player.auction.Status;

/**
 * Created by nikiforos on 14/09/15.
 */
// TODO: move to test package and use real implementation in src
public class MockAuctionAction implements IAuctionAction {
    @Override
    public Score chooseNextScore(Hand hand, int currentScore) {
        final Score score = new Score();
        score.setSafeScore(currentScore++);
        return score;
    }

    @Override
    public AuctionStance chooseNextStance(Player playerDeciding, int currentScore) {
        final AuctionStance auctionStance = new AuctionStance();
        auctionStance.setStatus(Status.IN_AUCTION);
        auctionStance.setScore(chooseNextScore(playerDeciding.getHand(), currentScore));
        return auctionStance;
    }
}
