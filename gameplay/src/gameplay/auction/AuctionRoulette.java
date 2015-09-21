package gameplay.auction;

import game.player.Player;
import game.table.GameTable;

/**
 * Created by nikiforos on 18/09/15.
 */
public class AuctionRoulette {

    public void execute(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        int playerInTurnIndex = 0;
        // 0-4) verify auction is still ongoing
        while (!isAuctionOver(gameTable)) {
            Player playerInTurn = players[playerInTurnIndex];

            // 1) player takes his decision
            int currentScore = gameTable.getAuctionScore();
            playerInTurn.performAuctionAction(currentScore);

            // 2) update game table
            updateGameTable();

            // 3) set next player to do the auction
            playerInTurnIndex = setNextPlayerToGo(playerInTurnIndex);
        }
    }

    private boolean isAuctionOver(GameTable gameTable) {
//        boolean auctionIsOver = false;
        return true;
    }

    private void updateGameTable() {

    }

    private int setNextPlayerToGo(int playerInTurn) {
        playerInTurn++;
        playerInTurn %= GameTable.NUMBER_OF_PLAYERS;
        return playerInTurn;
    }


}
