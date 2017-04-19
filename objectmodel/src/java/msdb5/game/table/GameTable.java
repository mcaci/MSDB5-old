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

    @Override
    public String toString() {
        return "GameTable{" +
                "deck=" + deck +
                ", players=" + Arrays.toString(players) +
                ", tableInfo=" + info +
                '}';
    }
}
