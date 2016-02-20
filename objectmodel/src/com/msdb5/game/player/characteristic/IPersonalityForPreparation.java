package com.msdb5.game.player.characteristic;

import com.msdb5.game.cardset.Hand;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.player.info.AuctionInfo;

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

    int evaluateHand(Hand handToEvaluate);

}
