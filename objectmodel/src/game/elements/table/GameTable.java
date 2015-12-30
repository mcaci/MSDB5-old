package game.elements.table;

import game.elements.cardset.Deck;
import game.elements.player.Player;
import game.elements.player.info.auction.Status;

import java.util.Arrays;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTable {

    public static final int NUMBER_OF_PLAYERS = 5;
    public static final int SIDE_DECK_SIZE = 5;
    public static final int NO_SIDE_DECK_SIZE = 0;

    private Deck deck;
    private Deck sideDeck;
    private Player[] players;
    private int auctionScore = 0;

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getSideDeck() {
        return sideDeck;
    }

    public void setSideDeck(Deck sideDeck) {
        this.sideDeck = sideDeck;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getAuctionScore() {
        return auctionScore;
    }

    public void setAuctionScore(int auctionScore) {
        this.auctionScore = auctionScore;
    }

    public void setWinningPlayer() {
        for (int i = 0; i < this.players.length; i++) {
            final Player player = players[i];
            if (!player.hasFolded()) {
                player.getAuctionInfo().setStatus(Status.AUCTION_WINNER);
            }
        }
    }

    public int getFoldedCount() {
        int foldedCount = 0;
        for (int i = 0; i < this.players.length; i++) {
            final Player player = players[i];
            if (player.hasFolded()) {
                foldedCount++;
            }
        }
        return foldedCount;
    }

    @Override
    public String toString() {
        return "GameTable{" +
                "deck=" + deck +
                "sideDeck=" + sideDeck +
                ", players=" + Arrays.toString(players) +
                ", auctionScore=" + auctionScore +
                '}';
    }
}
