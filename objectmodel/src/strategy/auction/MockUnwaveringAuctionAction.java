package strategy.auction;

import game.player.Player;
import game.player.auction.AuctionStance;
import game.player.auction.Status;

/**
 * Created by nikiforos on 25/09/15.
 */
public class MockUnwaveringAuctionAction extends MockAuctionAction {

    @Override
    int decideNextScore(int currentScore) {
        return currentScore + 7;
    }

    @Override
    public AuctionStance chooseNextStance(Player playerDeciding, int currentScore) {
        final AuctionStance auctionStance = playerDeciding.getAuctionStance();
        if (!auctionStance.getStatus().hasFolded()) {
            auctionStance.setStatus(Status.IN_AUCTION);
            auctionStance.setScore(chooseNextScore(playerDeciding.getHand(), currentScore));
        }
        return auctionStance;
    }
}
