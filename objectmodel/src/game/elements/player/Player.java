package game.elements.player;

import game.elements.cardset.Hand;
import game.elements.player.auction.AuctionInfo;
import game.elements.player.auction.Score;
import game.elements.player.strategy.auction.IAuctionPersonality;
import game.factory.cardset.HandFactory;

/**
 * Created by nikiforos on 30/08/15.
 */
public class Player {
    private final Hand hand;
    private final IAuctionPersonality auctionAction;
    private final AuctionInfo auctionInfo;

    Player(Hand hand, IAuctionPersonality auctionAction) {
        this.hand = hand;
        this.auctionAction = auctionAction;
        this.auctionInfo = new AuctionInfo();
    }

    public Player(IAuctionPersonality auctionAction) {
        this(new HandFactory().createHand(), auctionAction);
    }

    /**
     * Not to be used, just a way to fill the game table
     */
    @Deprecated
    public Player() {
        this(new IAuctionPersonality() {
            @Override
            public Score chooseNextScore(Hand hand, int currentScore) {
                return null;
            }

            @Override
            public AuctionInfo chooseNextStance(Player playerDeciding, int currentScore) {
                return null;
            }
        });
    }

    public Hand getHand() {
        return this.hand;
    }

    public AuctionInfo getAuctionInfo() {
        return auctionInfo;
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
