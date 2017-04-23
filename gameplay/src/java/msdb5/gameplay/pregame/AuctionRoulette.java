package msdb5.gameplay.pregame;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;
import msdb5.game.card.set.Deck;
import msdb5.game.player.Player;
import msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import msdb5.game.player.info.AuctionStatus;
import msdb5.game.table.GameTable;
import msdb5.game.table.GameTableInfo;
import msdb5.gameplay.GameRoulette;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * Created by nikiforos on 18/09/15.
 */
public class AuctionRoulette implements GameRoulette {

    public void executeOn(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        final boolean sideDeckPresent = false; //gameTable.getInfo().isSideDeckPresent();
        int playerInTurnIndex = 0;

        boolean isAuctionOver = false;
        Player playerInTurn = players[playerInTurnIndex];
        while (!isAuctionOver) {
            // 1) player takes his decision
            int currentScore = 0; //gameTable.getInfo().getAuctionScore();

            try {
                playerInTurn.performAuctionAction(currentScore);
            } catch (AuctionOnScoreOutOfBoundsException e) {
                // TODO: replace with logger and rethrow e
                System.out.println(currentScore);
                e.printStackTrace();
            }

            // 2) update game table
            int playerScore = playerInTurn.tellAuctionScore();
            if (currentScore < playerScore) {
//                gameTable.getInfo().setAuctionScore(playerScore);
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
        final int auctionScore = 0;// gameTable.getInfo().getAuctionScore();
        Player winner = findWinner(players, auctionScore);
//        gameTable.getInfo().setAuctionWinner(winner);

        // 6) winner chooses anion
        Card anionCard = winner.chooseCompanionCard();

        // 7) mix with side deck
        if (sideDeckPresent) {
            winner.swapCardsWithSideDeck(gameTable.getDeck());
        }

        // 8) confirm anion card
//        gameTable.getInfo().setPairedPlayerC/ard(anionCard);
    }

    private Card turnCardFromSideDeck(Deck sideDeck) {
        /* TODO: turn card, implies also that players can see it and adapt their evaluation to the card seen
         * for now it will just be a print to see that it is called */
        return null;
    }

    private Player findWinner(Player[] players, int auctionScore) {
        Player winner = null;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getAuctionInfo().getAuctionScore() == auctionScore) {
                winner = players[i];
            }
        }
        winner.getAuctionInfo().setAuctionStatus(AuctionStatus.AUCTION_WINNER);
        return winner;
    }

    private boolean isAuctionOver(GameTable gameTable) {
        final int foldedCount = 0;// TODO: gameTable.getInfo().getFoldedCount();
        final int auctionScore = 0;// gameTable.getInfo().getAuctionScore();
        return foldedCount == 4 || auctionScore >= Player.MAX_AUCTION_SCORE;
    }

    private int setNextPlayerToGo(int playerInTurn) {
        playerInTurn++;
        playerInTurn %= GameTableInfo.NUMBER_OF_PLAYERS;
        return playerInTurn;
    }

    public Player executeOn(Player... players) throws AuctionException {
        ExecutorService executorService = Executors.newFixedThreadPool(players.length);
        Future<Player> winner = executorService.submit(() -> {
            Stream.of(players).limit(4).forEach(player -> player.setAuctionStatusAs((() -> AuctionStatus.FOLDED)));
            players[4].setAuctionStatusAs(() -> AuctionStatus.AUCTION_WINNER);
            players[4].getAuctionInfo().setAuctionScore(120);
            return players[4];
        });
        try {
            return winner.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new AuctionException(e);
        }
    }
}
