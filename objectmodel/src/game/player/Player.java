package game.player;

import game.player.auction.AuctionInfo;

/**
 * Created by nikiforos on 30/08/15.
 */
public class Player {
    private final Hand hand = new Hand();
    private final AuctionInfo auctionInfo = new AuctionInfo();

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", auctionInfo=" + auctionInfo +
                '}';
    }

    public AuctionInfo getAuctionInfo() {
        return auctionInfo;
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
}
