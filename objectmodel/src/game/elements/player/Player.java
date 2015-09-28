package game.elements.player;

import game.elements.cardset.Hand;
import game.elements.player.auction.AuctionInfo;
import game.elements.player.strategy.auction.IAuctionAction;

/**
 * Created by nikiforos on 30/08/15.
 */
public class Player {
    private final Hand hand;
    private final IAuctionAction auctionAction;
    private AuctionInfo auctionInfo = new AuctionInfo();

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

    public AuctionInfo getAuctionInfo() {
        return auctionInfo;
    }

    public void setAuctionInfo(AuctionInfo auctionInfo) {
        this.auctionInfo = auctionInfo;
    }

    public IAuctionAction getAuctionAction() {
        return auctionAction;
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

    public void performAuctionAction(int currentScore) {
        this.auctionInfo = this.auctionAction.chooseNextStance(this, currentScore);
    }
}
