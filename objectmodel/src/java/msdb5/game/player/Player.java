package msdb5.game.player;

import msdb5.game.card.set.Deck;
import msdb5.game.card.set.Hand;
import msdb5.game.card.set.HandFactory;
import msdb5.game.player.characteristic.IPersonalityForPreparation;
import msdb5.game.player.characteristic.IPersonalityInGame;
import msdb5.game.player.info.AuctionInfo;
import msdb5.game.player.info.AuctionStatus;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by nikiforos on 30/08/15.
 */
public abstract class Player implements IPersonalityForPreparation, IPersonalityInGame {

    public static final byte MIN_AUCTION_SCORE = 60;
    public static final byte MAX_AUCTION_SCORE = 120;
    private final int id;
    private final AuctionInfo auctionInfo;
    private final Hand hand;

    public Player() {
        this(0);
    }

    public Player(int id) {
        this.id = id;
        auctionInfo = new AuctionInfo();
        hand = HandFactory.createEmptyHand();
    }

    public Hand getHand() {
        return this.hand;
    }

    public AuctionInfo getAuctionInfo() {
        return this.auctionInfo;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", auctionInfo=" + auctionInfo +
                ", hand=" + hand +
                '}';
    }

    public boolean getAuctionStatusFor(Predicate<AuctionStatus> statusPredicate) {
        return statusPredicate.test(this.auctionInfo.getAuctionStatus());
    }

    public int tellAuctionScore() {
        return this.auctionInfo.getAuctionScore();
    }

    public void setAuctionStatusAs(Supplier<AuctionStatus> statusSupplier) {
        this.getAuctionInfo().setAuctionStatus(statusSupplier.get());
    }

    public void drawCard(Deck fromDeck) {
        this.getHand().add(fromDeck.giveOneCard());
    }

    public abstract int actsOnAuction(AtomicInteger auctionValue);

    public abstract int decideAuctionScore(int previousScore);
}
