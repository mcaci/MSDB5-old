package game.player.characteristic;

import game.player.info.AuctionInfo;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IAuctionPersonality {
    /**
     * Takes as input the score of the auction and returns the decision of the player (score + status)
     *
     * @param currentScore
     * @return
     */
    AuctionInfo performAuctionAction(int currentScore) throws AuctionOnScoreOutOfBoundsException;

}
