package game.elements.player;

import game.elements.cardset.Hand;
import game.elements.player.characteristic.IAuctionPersonality;
import game.elements.player.info.AuctionInfo;
import game.elements.player.info.AuctionStatus;

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
        return auctionInfo.getAuctionStatus().actionWasDone();
    }

    public boolean isWinner() {
        return auctionInfo.getAuctionStatus().isWinner();
    }

    public boolean hasFolded() {
        return auctionInfo.getAuctionStatus().hasFolded();
    }

    public int tellScore() {
        return this.auctionInfo.getAuctionScore().getScore();
    }

    public void setAsAuctionWinner() {
        this.getAuctionInfo().setAuctionStatus(AuctionStatus.AUCTION_WINNER);
    }

}
