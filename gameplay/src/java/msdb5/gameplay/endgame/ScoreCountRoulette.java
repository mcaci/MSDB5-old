package msdb5.gameplay.endgame;

import msdb5.game.player.Player;
import msdb5.game.table.GameTable;
import msdb5.gameplay.GameRoulette;

/**
 * Created by nikiforos on 18/02/16.
 */
public class ScoreCountRoulette implements GameRoulette {

    public void executeOn(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        for (Player player : players) {
            byte playerScore = 0;

//            for (Card card : player.getCardPile()) {
//                playerScore += card.pointsForTheCard();
//            }

//            player.setScore(playerScore);
        }
    }
}
