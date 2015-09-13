package strategy.auction;

import game.player.Hand;
import game.player.Player;
import game.player.auction.AuctionStance;
import game.player.auction.Score;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IAuctionAction {

    Score chooseNextScore(Hand hand, int currentScore);

    AuctionStance chooseNextStance(Player playerDeciding, int currentScore);

}
