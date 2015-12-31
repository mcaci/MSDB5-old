package game.elements.player.auction.strategy;

import game.elements.cardset.Hand;
import game.elements.player.Player;
import game.elements.player.auction.info.AuctionInfo;
import game.elements.player.auction.info.Score;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IAuctionPersonality {

    byte MIN_AUCTION_SCORE = 60;
    byte MAX_AUCTION_SCORE = 120;

    Score chooseNextScore(Hand hand, int currentScore);

    AuctionInfo chooseNextStance(Player playerDeciding, int currentScore);

}
