package msdb5.game.player;

import msdb5.game.card.set.Hand;
import msdb5.game.player.characteristic.IPersonalityForPreparation;
import msdb5.game.player.characteristic.IPersonalityInGame;
import msdb5.game.player.info.AuctionInfo;
import msdb5.game.player.info.AuctionStatus;

import java.util.HashSet;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by nikiforos on 30/08/15.
 */
public abstract class Player implements IPersonalityForPreparation, IPersonalityInGame {

    public static final byte MIN_AUCTION_SCORE = 60;
    public static final byte MAX_AUCTION_SCORE = 120;
    private final AuctionInfo auctionInfo = new AuctionInfo();
    private final Hand hand = new Hand(new HashSet<>());

    public Hand getHand() {
        return this.hand;
    }

    public AuctionInfo getAuctionInfo() {
        return this.auctionInfo;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", auctionInfo=" + auctionInfo +
                '}';
    }

    public boolean getAuctionStatusFor(Predicate<AuctionStatus> statusPredicate) {
        return statusPredicate.test(this.auctionInfo.getAuctionStatus());
    }

    public byte tellAuctionScore() {
        return (byte) this.auctionInfo.getAuctionScore();
    }

    public void setAuctionStatusAs(Supplier<AuctionStatus> statusSupplier) {
        this.getAuctionInfo().setAuctionStatus(statusSupplier.get());
    }

}
