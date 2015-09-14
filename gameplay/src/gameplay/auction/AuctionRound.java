package gameplay.auction;

import game.elements.GameTable;
import game.player.Player;

/**
 * Created by nikiforos on 01/09/15.
 */
public class AuctionRound {

    public void performAuctionRound(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        int currentScore = gameTable.getAuctionScore();
        // execute round
        currentScore = executeRound(players, currentScore);
        // verify if there is a winner
        boolean isThereAWinner = isThereAWinner(players);
        // update table score globally
        gameTable.setAuctionScore(currentScore);
        gameTable.setAuctionIsOver(isThereAWinner);
    }

    private boolean isThereAWinner(Player[] players) {
        boolean isWinnerPresent = false;
        for (int i = 0; i < players.length && !isWinnerPresent; i++) {
            isWinnerPresent |= players[i].isWinner();
        }
        return isWinnerPresent;
    }

    private int executeRound(Player[] players, int currentScore) {
        for (int i = 0; i < players.length; i++) {
            final Player currentPlayerPlaying = players[i];
            // perform auction action
            currentPlayerPlaying.performAuctionAction(currentScore);
            // update table score locally
            if (!currentPlayerPlaying.hasFolded()) {
                currentScore = currentPlayerPlaying.getAuctionStance().getScore().getScore();
            }
        }
        return currentScore;
    }
}
