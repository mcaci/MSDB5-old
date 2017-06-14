package msdb5.gameplay.ingame;

import msdb5.game.card.Card;
import msdb5.game.player.Player;
import msdb5.game.table.GameTable;
import msdb5.gameplay.GameRoulette;

/**
 * Created by mcaci on 6/14/17.
 */
public class SimpleIngameRoulette implements GameRoulette {

    @Override
    public void executeOn(GameTable gameTable) {
        Player playerOne = gameTable.getPlayers()[0];
        for (Player player : gameTable.getPlayers()) {
            Card chosen = player.getHand().getCardSet().iterator().next();
            player.getHand().getCardSet().remove(chosen);
            playerOne.getCollectedCards().add(chosen);
        }
    }

}
