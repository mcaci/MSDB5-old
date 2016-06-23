package com.msdb5.gameplay.pregame;

import com.msdb5.game.cardset.Deck;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.player.Player;
import com.msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import com.msdb5.game.player.info.AuctionInfo;
import com.msdb5.game.player.info.AuctionScore;
import com.msdb5.game.player.info.AuctionStatus;
import com.msdb5.game.table.GameTable;
import com.msdb5.game.table.GameTableInfo;
import com.msdb5.gameplay.GameRoulette;

/**
 * Created by nikiforos on 18/09/15.
 */
public class AuctionRoulette implements GameRoulette {

    public void executeOn(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        final boolean sideDeckPresent = gameTable.getGameTableInfo().isSideDeckPresent();
        int playerInTurnIndex = 0;

        boolean isAuctionOver = false;
        Player playerInTurn = players[playerInTurnIndex];
        while (!isAuctionOver) {
            // 1) player takes his decision
            int currentScore = gameTable.getGameTableInfo().getAuctionScore();

            AuctionInfo actionResult; // useless to keep it
            try {
                actionResult = playerInTurn.performAuctionAction(currentScore);
            } catch (AuctionOnScoreOutOfBoundsException e) {
                // TODO: replace with logger and rethrow e
                System.out.println(currentScore);
                e.printStackTrace();
            }

            // 2) update game table
            int playerScore = playerInTurn.tellAuctionScore();
            if (currentScore < playerScore) {
                gameTable.getGameTableInfo().setAuctionScore(playerScore);
            }

            // 2.1) turn card if side deck is present
            if (sideDeckPresent &&
                    playerScore >= ScoreForTurningSideDeckCard.nextTurningAt(currentScore).getEquivalentInteger()) {
                turnCardFromSideDeck(gameTable.getDeck());
                System.out.println("turning card at " + ScoreForTurningSideDeckCard.nextTurningAt(currentScore) + "(" + playerScore + ")");
            }

            // 3) set next player to do the pregame
            playerInTurnIndex = setNextPlayerToGo(playerInTurnIndex);
            playerInTurn = players[playerInTurnIndex];

            // 4) verify pregame is still ongoing
            isAuctionOver = isAuctionOver(gameTable);
        }
        // 5) set last player remaining as winner
        final int auctionScore = gameTable.getGameTableInfo().getAuctionScore();
        Player winner = findWinner(players, auctionScore);
        gameTable.getGameTableInfo().setAuctionWinner(winner);

        // 6) winner chooses companion
        Card companionCard = winner.chooseCompanionCard();

        // 7) mix with side deck
        if (sideDeckPresent) {
            winner.swapCardsWithSideDeck(gameTable.getDeck());
        }

        // 8) confirm companion card
        gameTable.getGameTableInfo().setPairedPlayerCard(companionCard);
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
        final int auctionScore = gameTable.getGameTableInfo().getAuctionScore();
        return foldedCount == 4 || auctionScore >= AuctionScore.MAX_SCORE;
    }

    private int setNextPlayerToGo(int playerInTurn) {
        playerInTurn++;
        playerInTurn %= GameTableInfo.NUMBER_OF_PLAYERS;
        return playerInTurn;
    }

}
