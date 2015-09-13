package game.player;

import game.player.auction.AuctionStance;

/**
 * Created by nikiforos on 30/08/15.
 */
public class Player {
    private final Hand hand = new Hand();
    private final AuctionStance auctionStance = new AuctionStance();

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", auctionStance=" + auctionStance +
                '}';
    }

    public AuctionStance getAuctionStance() {
        return auctionStance;
    }

    public boolean hasActed() {
        return auctionStance.getStatus().actionWasDone();
    }

    public boolean isWinner() {
        return auctionStance.getStatus().isWinner();
    }

    public boolean hasFolded() {
        return auctionStance.getStatus().hasFolded();
    }
}
