package game.elements.player.auction.strategy;

import game.elements.player.auction.info.AuctionInfo;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IAuctionPersonality {

    byte MIN_AUCTION_SCORE = 60;
    byte MAX_AUCTION_SCORE = 120;

    AuctionInfo performAuctionAction(int currentScore);

}
