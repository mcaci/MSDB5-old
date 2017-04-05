package msdb5.game.table;

import msdb5.game.card.set.Deck;
import msdb5.game.player.Player;

import java.util.Arrays;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTable {

    private final GameTableInfo info = new GameTableInfo();
    private Deck deck;
    private Player[] players;

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
                ", players=" + Arrays.toString(players) +
                ", tableInfo=" + info +
                '}';
    }
}
