package msdb5.game.table;

import msdb5.game.card.set.SideDeck;
import msdb5.game.player.Player;

import java.util.Arrays;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTable {

    private Player[] players;
    private SideDeck sideDeck;

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public SideDeck getSideDeck() {
        return sideDeck;
    }

    public void setSideDeck(SideDeck sideDeck) {
        this.sideDeck = sideDeck;
    }

    @Override
    public String toString() {
        return "GameTable{" +
               "Players=" + Arrays.toString(players) +
               ", sideDeck=" + sideDeck +
               '}';
    }
}
