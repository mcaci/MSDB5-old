package msdb5.gameplay.player;

import msdb5.game.card.Card;
import msdb5.game.card.set.Deck;
import msdb5.game.card.set.Hand;
import msdb5.game.player.Player;
import msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import msdb5.game.player.info.AuctionInfo;
import msdb5.game.player.info.AuctionStatus;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

/**
 * Created by mcaci on 4/12/17.
 */
public class TestPlayerForGamePlayer extends Player {

    ToIntBiFunction<Integer, Hand> chooseNextScoreFunction = (currentScore, hand) -> 80;
    BiPredicate<Integer, Hand> foldingDecision = (currentScore, hand) -> currentScore > 90;

    @Override
    public void swapCardsWithSideDeck(Deck deck) {

    }

    @Override
    public AuctionInfo performAuctionAction(int currentScore) throws AuctionOnScoreOutOfBoundsException {
        return null;
    }

    @Override
    public Card chooseCompanionCard() {
        return null;
    }

    @Override
    public int actsOnAuction(AtomicInteger auctionValue) {
        int currentAuctionValue = auctionValue.get();
        boolean wantsToFold = foldingDecision.test(currentAuctionValue, this.getHand());
        if(wantsToFold){
            this.setAuctionStatusAs(() -> AuctionStatus.FOLDED);
        }
        else {
            this.setAuctionStatusAs(() -> AuctionStatus.IN_AUCTION);
            currentAuctionValue = auctionValue.updateAndGet(this::decideAuctionScore);
        }
        return currentAuctionValue;
    }

    @Override
    public int decideAuctionScore(int currentScore) {
        return chooseNextScoreFunction.applyAsInt(currentScore, this.getHand());
    }
}
