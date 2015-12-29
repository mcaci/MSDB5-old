package game.elements.player.strategy.auction;

import game.elements.player.Player;
import game.elements.player.info.auction.AuctionInfo;
import game.elements.player.info.auction.Status;

/**
 * Created by nikiforos on 25/09/15.
 */
public class MockUnwaveringAuctionPersonality extends MockAuctionPersonality {

    public static final int SCORE_INCREMENT = 7;

    @Override
    int decideNextScore(int currentScore) {
        return currentScore + SCORE_INCREMENT;
    }

    @Override
    public AuctionInfo chooseNextStance(Player playerDeciding, int currentScore) {
        final AuctionInfo auctionInfo = playerDeciding.getAuctionInfo();
        if (!auctionInfo.getStatus().hasFolded()) {
            auctionInfo.setStatus(Status.IN_AUCTION);
            auctionInfo.setScore(chooseNextScore(playerDeciding.getHand(), currentScore));
        }
        return auctionInfo;
    }
}
