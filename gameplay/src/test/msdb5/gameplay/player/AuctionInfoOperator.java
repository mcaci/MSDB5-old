package msdb5.gameplay.player;

import msdb5.game.card.set.Hand;
import msdb5.game.player.info.AuctionStatus;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

/**
 * Created by nikiforos on 22/04/17.
 */
public class AuctionInfoOperator {
    private final IntUnaryOperator scoreDecisionOperator;
    private boolean wantsToFold;
    private Supplier<AuctionStatus> auctionStatusSupplier;
    private ToIntFunction<AtomicInteger> auctionValueOperator;

    public AuctionInfoOperator(AtomicInteger auctionValue, Hand hand, BiPredicate<Integer, Hand> foldingDecision, ToIntBiFunction<Integer, Hand> chooseNextScoreFunction) {
        this.wantsToFold = foldingDecision.test(auctionValue.get(), hand);
        this.scoreDecisionOperator = x -> chooseNextScoreFunction.applyAsInt(auctionValue.get(), hand);
        invoke();
    }

    public Supplier<AuctionStatus> getAuctionStatusSupplier() {
        return auctionStatusSupplier;
    }

    public ToIntFunction<AtomicInteger> getAuctionValueOperator() {
        return auctionValueOperator;
    }

    private void invoke() {
        if (wantsToFold) {
            auctionStatusSupplier = () -> AuctionStatus.FOLDED;
            auctionValueOperator = AtomicInteger::get;
        } else {
            auctionStatusSupplier = () -> AuctionStatus.IN_AUCTION;
            auctionValueOperator = auctionVal -> auctionVal.updateAndGet(this.scoreDecisionOperator);
        }
    }
}
