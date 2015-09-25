package gameplay.auction;

import game.player.Player;
import game.player.auction.Status;
import game.table.GameTable;
import strategy.auction.IAuctionAction;

/**
 * Created by nikiforos on 18/09/15.
 */
public class AuctionRoulette {

    public void execute(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        int playerInTurnIndex = 0;

        boolean isAuctionOver = false;
        Player playerInTurn = players[playerInTurnIndex];
        while (!isAuctionOver) {
            // 1) player takes his decision
            int currentScore = gameTable.getAuctionScore();
            playerInTurn.performAuctionAction(currentScore);

            // 2) update game table
            int playerScore = playerInTurn.getAuctionStance().getScore().getScore();
            if (currentScore < playerScore) {
                gameTable.setAuctionScore(playerScore);
            }

            // 3) set next player to do the auction
            playerInTurnIndex = setNextPlayerToGo(playerInTurnIndex);
            playerInTurn = players[playerInTurnIndex];

            // 4) verify auction is still ongoing
            isAuctionOver = isAuctionOver(gameTable);
        }
        // 5) set last player remaining as winner
        final int auctionScore = gameTable.getAuctionScore();
        setWinner(players, auctionScore);
    }

    private void setWinner(Player[] players, int auctionScore) {
        Player winner = null;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getAuctionStance().getScore().getScore() == auctionScore) {
                winner = players[i];
            }
        }
        winner.getAuctionStance().setStatus(Status.AUCTION_WINNER);
    }

    private boolean isAuctionOver(GameTable gameTable) {
        final int foldedCount = gameTable.getFoldedCount();
        final int auctionScore = gameTable.getAuctionScore();
        return foldedCount == 4 || auctionScore >= IAuctionAction.MAX_AUCTION_SCORE;
    }

    private int setNextPlayerToGo(int playerInTurn) {
        playerInTurn++;
        playerInTurn %= GameTable.NUMBER_OF_PLAYERS;
        return playerInTurn;
    }

}
