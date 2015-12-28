package game.elements.player;

import game.elements.cardset.Hand;
import game.elements.player.auction.AuctionInfo;
import game.elements.player.strategy.auction.IAuctionPersonality;
import game.factory.cardset.HandFactory;

/**
 * Created by nikiforos on 30/08/15.
 */
public class Player {
    private final Hand hand;
    private final IAuctionPersonality auctionAction;
    private AuctionInfo auctionInfo = new AuctionInfo();

    Player(Hand hand, IAuctionPersonality auctionAction) {
        this.hand = hand;
        this.auctionAction = auctionAction;
    }

    public Player(IAuctionPersonality auctionAction) {
        this(new HandFactory(true).createHand(), auctionAction);
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

    public IAuctionPersonality getAuctionAction() {
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
        AuctionInfo infoAfterAction = this.auctionAction.chooseNextStance(this, currentScore);
        this.auctionInfo.update(infoAfterAction);
    }
}
