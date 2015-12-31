package game.elements.player;

import game.elements.cardset.Hand;
import game.elements.player.auction.info.AuctionInfo;
import game.elements.player.auction.strategy.IAuctionPersonality;

/**
 * Created by nikiforos on 30/08/15.
 */
public abstract class Player implements IAuctionPersonality {
    private final AuctionInfo auctionInfo = new AuctionInfo();
    private Hand hand = new Hand();

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public AuctionInfo getAuctionInfo() {
        return auctionInfo;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", auctionInfo=" + auctionInfo +
                '}';
    }

    public boolean hasActed() {
        return auctionInfo.getStatus().actionWasDone();
    }

    public boolean isWinner() {
        return auctionInfo.getStatus().isWinner();
    }

    public boolean hasFolded() {
        return auctionInfo.getStatus().hasFolded();
    }

    public int tellScore() {
        return this.auctionInfo.getScore().getScore();
    }

}
