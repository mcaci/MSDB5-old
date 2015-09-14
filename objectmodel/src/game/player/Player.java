package game.player;

import game.player.auction.AuctionStance;
import strategy.auction.IAuctionAction;

/**
 * Created by nikiforos on 30/08/15.
 */
public class Player {
    final IAuctionAction auctionAction;
    private final Hand hand = new Hand();
    private AuctionStance auctionStance = new AuctionStance();

    public Player(IAuctionAction auctionAction) {
        this.auctionAction = auctionAction;
    }

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

    public void performAuctionAction(int currentScore) {
        this.auctionStance = this.auctionAction.chooseNextStance(this, currentScore);
    }
}
