package msdb5.gameplay.ingame;

import msdb5.game.table.GameTable;
import msdb5.gameplay.GameRoulette;

/**
 * Created by mcaci on 6/14/17.
 */
public class SimpleIngameRoulette implements GameRoulette {

    @Override
    public void executeOn(GameTable gameTable) {
        GameRoulette gameRoulette;
        int size = gameTable.getPlayers()[0].getHand().size();
        while (size > 0) {
            gameRoulette = new SimpleIngameRoundRoulette();
            gameRoulette.executeOn(gameTable);
            size--;
        }
    }

}
