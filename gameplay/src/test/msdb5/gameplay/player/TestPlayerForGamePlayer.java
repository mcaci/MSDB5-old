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
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;

/**
 * Created by mcaci on 4/12/17.
 */
public class TestPlayerForGamePlayer extends Player {

    public TestPlayerForGamePlayer(int id) {
        super(id);
    }

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
    public AtomicInteger actsOnAuction(AtomicInteger auctionValue, BiPredicate<Integer, Hand> foldingDecision, ToIntBiFunction<Integer, Hand> chooseNextScoreFunction) {
        AuctionInfoOperator auctionInfoOperator = new AuctionInfoOperator(auctionValue, this.getHand(), foldingDecision, chooseNextScoreFunction);
        updatePersonalAuctionStatus(auctionInfoOperator.getAuctionStatusSupplier());
        updatePersonalAuctionValue(auctionInfoOperator.getAuctionValueOperator().applyAsInt(auctionValue));
        return auctionValue;
    }

    @Override
    public BiPredicate<Integer, Hand> getFoldingDecision() {
        return (currentScore, hand) -> currentScore > 90;
    }

    @Override
    public ToIntBiFunction<Integer, Hand> getChooseNextScoreFunction() {
        return (currentScore, hand) -> ++currentScore;
    }

    private void updatePersonalAuctionStatus(Supplier<AuctionStatus> auctionStatusSupplier) {
        if (auctionStatusSupplier.get() != this.getAuctionInfo().getAuctionStatus()) {
            this.setAuctionStatusAs(auctionStatusSupplier);
        }
    }

    private void updatePersonalAuctionValue(int updatedAuctionValue) {
        if (this.getAuctionStatusFor(status -> AuctionStatus.FOLDED != status)) {
            this.getAuctionInfo().setAuctionScore(updatedAuctionValue);
        }
    }
}
