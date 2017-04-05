package msdb5.gameplay;

import msdb5.game.table.GameTable;

/**
 * Interface that all game actions implement when rounds are involved
 * Created by nikiforos on 18/02/16.
 */
public interface GameRoulette {

    void executeOn(GameTable gameTable);
}
