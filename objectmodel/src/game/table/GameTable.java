package game.table;

import game.elements.cardset.Deck;
import game.player.Player;

import java.util.Arrays;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTable {

    public static final int NUMBER_OF_PLAYERS = 5;
    public static final int SIDE_DECK_SIZE = 5;
    public static final int NO_SIDE_DECK_SIZE = 0;

    private Deck deck;
    private Player[] players;
    private int auctionScore = 0;
    private boolean auctionIsOver = false;

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
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

    public boolean isAuctionIsOver() {
        return auctionIsOver;
    }

    public void setAuctionIsOver(boolean auctionIsOver) {
        this.auctionIsOver = auctionIsOver;
    }

    @Override
    public String toString() {
        return "GameTable{" +
                "deck=" + deck +
                ", players=" + Arrays.toString(players) +
                ", auctionScore=" + auctionScore +
                ", auctionIsOver=" + auctionIsOver +
                '}';
    }


}
