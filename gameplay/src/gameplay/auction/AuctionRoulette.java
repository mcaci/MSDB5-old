package gameplay.auction;

import game.cardset.Deck;
import game.cardset.card.Card;
import game.player.Player;
import game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import game.player.info.AuctionInfo;
import game.player.info.AuctionScore;
import game.player.info.AuctionStatus;
import game.table.GameTable;
import game.table.GameTableInfo;

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
            int currentScore = gameTable.getInfo().getAuctionScore();

            AuctionInfo actionResult;
            try {
                actionResult = playerInTurn.performAuctionAction(currentScore);
            } catch (AuctionOnScoreOutOfBoundsException e) {
                // TODO: replace with logger and rethrow e
                System.out.println(currentScore);
                e.printStackTrace();
            }

            // 2) update game table
            int playerScore = playerInTurn.tellScore();
            if (currentScore < playerScore) {
                gameTable.getInfo().setAuctionScore(playerScore);
            }

            // 2.1) turn card if side deck is present
            if (gameTable.getInfo().isSideDeckPresent() &&
                    playerScore >= ScoreForTurningSideDeckCard.nextTurningAt(currentScore).getEquivalentInteger()) {
                turnCardFromSideDeck(gameTable.getDeck());
                System.out.println("turning card at " + ScoreForTurningSideDeckCard.nextTurningAt(currentScore) + "(" + playerScore + ")");
            }

            // 3) set next player to do the auction
            playerInTurnIndex = setNextPlayerToGo(playerInTurnIndex);
            playerInTurn = players[playerInTurnIndex];

            // 4) verify auction is still ongoing
            isAuctionOver = isAuctionOver(gameTable);
        }
        // 5) set last player remaining as winner
        final int auctionScore = gameTable.getInfo().getAuctionScore();
        Player winner = findWinner(players, auctionScore);
        gameTable.getInfo().setAuctionWinner(winner);
    }

    private Card turnCardFromSideDeck(Deck sideDeck) {
        /* TODO: turn card, implies also that players can see it and adapt their evaluation to the card seen
         * for now it will just be a print to see that it is called */
        return null;
    }

    private Player findWinner(Player[] players, int auctionScore) {
        Player winner = null;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getAuctionInfo().getAuctionScore().getScore() == auctionScore) {
                winner = players[i];
            }
        }
        winner.getAuctionInfo().setAuctionStatus(AuctionStatus.AUCTION_WINNER);
        return winner;
    }

    private boolean isAuctionOver(GameTable gameTable) {
        final int foldedCount = gameTable.getFoldedCount();
        final int auctionScore = gameTable.getInfo().getAuctionScore();
        return foldedCount == 4 || auctionScore >= AuctionScore.MAX_SCORE;
    }

    private int setNextPlayerToGo(int playerInTurn) {
        playerInTurn++;
        playerInTurn %= GameTableInfo.NUMBER_OF_PLAYERS;
        return playerInTurn;
    }

}
