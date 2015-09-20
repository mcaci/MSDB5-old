package strategy.auction;

import game.elements.cardset.Hand;
import game.player.Player;
import game.player.auction.AuctionStance;
import game.player.auction.Score;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IAuctionAction {

    byte MIN_AUCTION_SCORE = 60;
    byte MAX_AUCTION_SCORE = 120;

    Score chooseNextScore(Hand hand, int currentScore);

    AuctionStance chooseNextStance(Player playerDeciding, int currentScore);

}
