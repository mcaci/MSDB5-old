package com.msdb5.game.player.ia.player;

import com.msdb5.game.cardset.Deck;
import com.msdb5.game.cardset.Hand;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.player.Player;
import com.msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import com.msdb5.game.player.info.AuctionInfo;

/**
 * Created by nikiforos on 13/02/16.
 */
public abstract class ConcretePlayer extends Player {

    @Override
    public AuctionInfo performAuctionAction(int currentScore) throws AuctionOnScoreOutOfBoundsException {
        return null;
    }

    @Override
    public Card chooseCompanionCard() {
        return null;
    }

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        return 0;
    }

    @Override
    public void swapCardsWithSideDeck(Deck deck) {
        return; // TODO: real impl
    }
}
