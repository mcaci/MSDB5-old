package game.player;

import game.cardset.Hand;
import game.cardset.card.Card;
import game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import game.player.info.AuctionInfo;

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
}
