package game.elements.player.strategy.auction;

import game.elements.cardset.DeckAwareHand;
import game.elements.player.Player;
import game.elements.player.auction.AuctionInfo;
import game.elements.player.auction.Score;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IAuctionAction {

    byte MIN_AUCTION_SCORE = 60;
    byte MAX_AUCTION_SCORE = 120;

    Score chooseNextScore(DeckAwareHand deckAwareHand, int currentScore);

    AuctionInfo chooseNextStance(Player playerDeciding, int currentScore);

}
