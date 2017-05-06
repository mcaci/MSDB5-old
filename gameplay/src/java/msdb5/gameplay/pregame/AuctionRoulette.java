package msdb5.gameplay.pregame;

import msdb5.game.card.Card;
import msdb5.game.card.set.SideDeck;
import msdb5.game.player.Player;
import msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import msdb5.game.player.info.AuctionStatus;
import msdb5.game.table.GameTable;
import msdb5.game.table.GameTableInfo;
import msdb5.gameplay.GameRoulette;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nikiforos on 18/09/15.
 */
public class AuctionRoulette implements GameRoulette {

    public static final int AUCTION_BASE = 60;
    public static final int AUCTION_MAX = 120;

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
                turnCardFromSideDeck(gameTable.getSideDeck());
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
            winner.swapCardsWithSideDeck(gameTable.getSideDeck());
        }

        // 8) confirm anion card
//        gameTable.getInfo().setPairedPlayerC/ard(anionCard);
    }

    private Card turnCardFromSideDeck(SideDeck sideDeck) {
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
        AtomicInteger auctionValue = new AtomicInteger(60);
        ExecutorService executorService = Executors.newFixedThreadPool(players.length);
        Future<Player> winner = null;
        try {
            for (Player player : players) {
                winner = executorService.submit(() -> {
                    while (player.getAuctionStatusFor(this::isActive)) {
                        player.actsOnAuction(auctionValue, player.getFoldingDecision(), player.getChooseNextScoreFunction());
                    }
                    players[4].setAuctionStatusAs(() -> AuctionStatus.AUCTION_WINNER);
                    players[4].getAuctionInfo().setAuctionScore(120);
                    return players[4];
                });
            }
        } finally {
            if (executorService != null) executorService.shutdown();
        }
        try {
            while (executorService.isTerminated()) {
                executorService.awaitTermination(1, TimeUnit.SECONDS);
            }
            return winner.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new AuctionException(e);
        }
    }

    public boolean isActive(AuctionStatus status) {
        return status == AuctionStatus.NOT_STARTED || status == AuctionStatus.IN_AUCTION;
    }
}
