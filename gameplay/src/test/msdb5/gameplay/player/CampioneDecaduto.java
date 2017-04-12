package msdb5.gameplay.player;

import msdb5.game.card.Card;
import msdb5.game.card.set.Deck;
import msdb5.game.player.Player;
import msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import msdb5.game.player.info.AuctionInfo;

/**
 * Created by mcaci on 4/12/17.
 */
public class CampioneDecaduto extends Player {
    public void swapCardsWithSideDeck(Deck deck) {

    }

    public AuctionInfo performAuctionAction(int currentScore) throws AuctionOnScoreOutOfBoundsException {
        return null;
    }

    public Card chooseCompanionCard() {
        return null;
    }
}
