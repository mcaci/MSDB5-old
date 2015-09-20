package game.player;

import game.elements.cardset.Hand;
import game.player.auction.AuctionStance;
import strategy.auction.IAuctionAction;

/**
 * Created by nikiforos on 30/08/15.
 */
public class Player {
    private final Hand hand;
    private final IAuctionAction auctionAction;
    private AuctionStance auctionStance = new AuctionStance();

    Player(Hand hand, IAuctionAction auctionAction) {
        this.hand = hand;
        this.auctionAction = auctionAction;
    }

    public Player(IAuctionAction auctionAction) {
        this(new Hand(), auctionAction);
    }

    public Hand getHand() {
        return this.hand;
    }

    public AuctionStance getAuctionStance() {
        return auctionStance;
    }

    public void setAuctionStance(AuctionStance auctionStance) {
        this.auctionStance = auctionStance;
    }

    public IAuctionAction getAuctionAction() {
        return auctionAction;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", auctionStance=" + auctionStance +
                '}';
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

    public int tellScore() {
        return this.auctionStance.getScore().getScore();
    }

    public void performAuctionAction(int currentScore) {
        this.auctionStance = this.auctionAction.chooseNextStance(this, currentScore);
    }
}
