package msdb5.game.table;

import msdb5.game.card.Card;
import msdb5.game.player.Player;

/**
 * Created by nikiforos on 02/01/16.
 */
public class GameTableInfo {

    public static final byte NUMBER_OF_PLAYERS = 5;
    public static final byte SIDE_DECK_SIZE = 5;
    public static final byte NO_SIDE_DECK_SIZE = 0;
    public static final byte MIN_AUCTION_SCORE = 60;
    public static final byte MAX_AUCTION_SCORE = 120;

    private int auctionScore = MIN_AUCTION_SCORE;
    private Card pairedPlayerCard = null;
    private boolean sideDeckPresent = false;
    private Player auctionWinner = null;

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

    public Player getAuctionWinner() {
        return auctionWinner;
    }

    public void setAuctionWinner(Player auctionWinner) {
        this.auctionWinner = auctionWinner;
    }

    @Override
    public String toString() {
        return "GameTableInfo{" +
                "auctionScore=" + auctionScore +
                ", pairedPlayerCard=" + pairedPlayerCard +
                ", sideDeckPresent=" + sideDeckPresent +
                ", auctionWinner=" + auctionWinner +
                '}';
    }
}
