package game.elements.player.auction;

import game.elements.player.auction.info.AuctionInfo;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IAuctionPersonality {

    byte MIN_AUCTION_SCORE = 60;
    byte MAX_AUCTION_SCORE = 120;

    /**
     * Takes as input the score of the auction and returns the decision of the player (score + status)
     *
     * @param currentScore
     * @return
     */
    AuctionInfo performAuctionAction(int currentScore);

}
