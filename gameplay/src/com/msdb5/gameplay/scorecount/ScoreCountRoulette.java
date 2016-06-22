package com.msdb5.gameplay.scorecount;

import com.msdb5.game.player.Player;
import com.msdb5.game.table.GameTable;
import com.msdb5.gameplay.GameRoulette;

/**
 * Created by nikiforos on 18/02/16.
 */
public class ScoreCountRoulette implements GameRoulette {

    public void executeOn(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        for (Player player: players) {
            player.setScore((byte)24);
        }
    }
}
