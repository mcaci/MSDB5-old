package msdb5.game.player;

import msdb5.game.card.Card;
import msdb5.game.card.set.*;
import msdb5.game.player.characteristic.IPersonalityForPreparation;
import msdb5.game.player.characteristic.IPersonalityInGame;
import msdb5.game.player.info.AuctionInfo;
import msdb5.game.player.info.AuctionStatus;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;

/**
 * Created by nikiforos on 30/08/15.
 */
public abstract class Player implements IPersonalityForPreparation, IPersonalityInGame {

    public static final byte MIN_AUCTION_SCORE = 60;
    public static final byte MAX_AUCTION_SCORE = 120;
    private final int id;
    private final AuctionInfo auctionInfo;
    private final Hand hand;
    private final CardSet<? extends Collection<Card>> collectedCards;
    private int gameScore = 0;

    public Player() {
        this(0);
    }

    public Player(int id) {
        this.id = id;
        this.auctionInfo = new AuctionInfo();
        this.hand = new HandFactory().create();
        this.collectedCards = new CollectedCardSetFactory().create();
    }

    public Hand getHand() {
        return this.hand;
    }

    public AuctionInfo getAuctionInfo() {
        return this.auctionInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return id == player.id;

    }

    @Override
    public int hashCode() {
        return id;
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

    public abstract int actsOnAuction(AtomicInteger auctionValue, BiPredicate<Integer, Hand> foldingDecision, ToIntBiFunction<Integer, Hand> chooseNextScoreFunction);

    public abstract BiPredicate<Integer, Hand> getFoldingDecision();

    public abstract ToIntBiFunction<Integer, Hand> getChooseNextScoreFunction();

    public CardSet<? extends Collection<Card>> getCollectedCards() {
        return collectedCards;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
}
