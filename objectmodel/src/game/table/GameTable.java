package game.table;

import game.cardset.Deck;
import game.player.Player;

import java.util.Arrays;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTable {

    private final GameTableInfo info = new GameTableInfo();
    private Deck deck;
    private Deck sideDeck;
    private Player[] players;

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

    public GameTableInfo getInfo() {
        return info;
    }

    public void setWinningPlayer() {
        for (int i = 0; i < this.players.length; i++) {
            final Player player = players[i];
            if (!player.hasFolded()) {
                player.setAsAuctionWinner();
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
                ", tableInfo=" + info +
                '}';
    }
}
