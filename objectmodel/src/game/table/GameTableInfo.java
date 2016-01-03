package game.table;

import game.cardset.card.Card;

/**
 * Created by nikiforos on 02/01/16.
 */
public class GameTableInfo {

    public static final int NUMBER_OF_PLAYERS = 5;
    public static final int SIDE_DECK_SIZE = 5;
    public static final int NO_SIDE_DECK_SIZE = 0;

    private int auctionScore = 0;
    private Card compagno = null;

    public int getAuctionScore() {
        return auctionScore;
    }

    public void setAuctionScore(int auctionScore) {
        this.auctionScore = auctionScore;
    }

    public Card getCompagno() {
        return compagno;
    }

    public void setCompagno(Card compagno) {
        this.compagno = compagno;
    }

    @Override
    public String toString() {
        return "GameTableInfo{" +
                "auctionScore=" + auctionScore +
                ", compagno=" + compagno +
                '}';
    }
}
