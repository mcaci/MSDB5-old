package game.table;

import game.cardset.card.Card;
import game.player.info.AuctionScore;

/**
 * Created by nikiforos on 02/01/16.
 */
public class GameTableInfo {

    public static final int NUMBER_OF_PLAYERS = 5;
    public static final int SIDE_DECK_SIZE = 5;
    public static final int NO_SIDE_DECK_SIZE = 0;

    private int auctionScore = AuctionScore.MIN_SCORE;
    private Card pairedPlayerCard = null;
    private boolean sideDeckPresent = false;

    public int getAuctionScore() {
        return auctionScore;
    }

    public void setAuctionScore(int auctionScore) {
        this.auctionScore = auctionScore;
    }

    public Card getPairedPlayerCard() {
        return pairedPlayerCard;
    }

    public void setPairedPlayerCard(Card pairedPlayerCard) {
        this.pairedPlayerCard = pairedPlayerCard;
    }

    public boolean isSideDeckPresent() {
        return sideDeckPresent;
    }

    public void setSideDeckPresent(boolean sideDeckPresent) {
        this.sideDeckPresent = sideDeckPresent;
    }

    @Override
    public String toString() {
        return "GameTableInfo{" +
                "auctionScore=" + auctionScore +
                ", pairedPlayerCard=" + pairedPlayerCard +
                ", sideDeckPresent=" + sideDeckPresent +
                '}';
    }
}
