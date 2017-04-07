package msdb5.game.player.characteristic;

import msdb5.game.card.set.Hand;
import msdb5.game.card.Card;
import msdb5.game.player.info.AuctionInfo;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IPersonalityForPreparation {
    /**
     * Takes as input the score of the auction and returns the decision of the player (score + status)
     *
     * @param currentScore
     * @return
     */
    AuctionInfo performAuctionAction(int currentScore) throws AuctionOnScoreOutOfBoundsException;

    Card chooseCompanionCard();

}
