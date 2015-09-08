package game.player;

import game.player.auction.PersonalAuctionInfo;

/**
 * Created by nikiforos on 30/08/15.
 */
public class Player {
    private final Hand hand;
    private final PersonalAuctionInfo personalAuctionInfo = new PersonalAuctionInfo();

    public Player() {
        this.hand = new Hand();
    }

    public Player(Hand hand) {
        this.hand = hand;
    }


    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", personalAuctionInfo=" + personalAuctionInfo +
                '}';
    }

    public PersonalAuctionInfo getPersonalAuctionInfo() {
        return personalAuctionInfo;
    }

    public boolean hasActed() {
        return personalAuctionInfo.getAuctionPlayerStance().actionWasDone();
    }

    public boolean isWinner() {
        return personalAuctionInfo.getAuctionPlayerStance().isWinner();
    }

    public boolean hasFolded() {
        return personalAuctionInfo.getAuctionPlayerStance().hasFolded();
    }
}
