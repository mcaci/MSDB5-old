package gameplay.auction;

import game.elements.GameTable;
import game.factory.GameTableFactory;
import game.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import strategy.auction.IAuctionAction;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 01/09/15.
 */
public class AuctionRoundTest {

    private AuctionRound auctionRoundTest;
    private GameTable inputOutputGameTable;

    @Before
    public void setUp() throws Exception {
        auctionRoundTest = new AuctionRound();

        // input players
        GameTableFactory gameTableFactory = new GameTableFactory();
        inputOutputGameTable = gameTableFactory.getCreatedGameTable();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("AuctionRoundTest: gameTable after the evaluation");
        System.out.println(inputOutputGameTable);
    }

    @Test
    public void testPerformAuctionRound() throws Exception {
        // output game table
        int originalScore = inputOutputGameTable.getAuctionScore();
        auctionRoundTest.performAuctionRound(inputOutputGameTable);

        // 1) the score of the table is greater than the original one
        int outputScore = inputOutputGameTable.getAuctionScore();
        boolean isCurrentScoreGreaterThanOriginal = outputScore > originalScore;
        if (isCurrentScoreGreaterThanOriginal) {
            // 1.1) all players have acted
            testAllPlayersHaveActed(inputOutputGameTable.getPlayers());
        } else {
            int winnerIndex = getIndexOfWinner();
            // 1.1) check there is a winner and four folded
            testWinnerCase(inputOutputGameTable.getPlayers(), winnerIndex);
            // 1.2) the winner has the greater auction score
            testWinnerScore(inputOutputGameTable.getPlayers(), winnerIndex);
        }

        // 2) all the players scores are different
        testAllScoresAreDifferent(inputOutputGameTable.getPlayers());
        // 3) all the players scores are between the valid range
        testAllScoresAreInTheValidRange(inputOutputGameTable.getPlayers());
    }

    private void testAllPlayersHaveActed(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            assertTrue(players[i].hasActed());
        }
    }

    private void testWinnerCase(Player[] players, int winnerIndex) {
        for (int i = 0; i < players.length; i++) {
            if (i != winnerIndex) {
                final Player player = players[i];
                assertTrue(player.toString() + " is not the winner so it should result as folded", player.hasFolded());
            }
        }
    }

    private void testWinnerScore(Player[] players, int winnerIndex) {
        byte winnerScore = players[winnerIndex].getAuctionStance().getScore().getScore();
        for (int i = 0; i < players.length; i++) {
            if (i != winnerIndex) {
                final Player player = players[i];
                assertTrue(player.toString() + " is not the winner so its auction score should be inferior",
                        winnerScore > player.getAuctionStance().getScore().getScore());
            }
        }
    }

    private void testAllScoresAreDifferent(Player[] players) {
        boolean conditionIsVerified = true;
        for (int i = 0; i < players.length && conditionIsVerified; i++) {
            byte iScore = players[i].getAuctionStance().getScore().getScore();
            for (int j = i + 1; j < players.length && conditionIsVerified; j++) {
                byte jScore = players[j].getAuctionStance().getScore().getScore();
                conditionIsVerified &= iScore != jScore;
            }
        }
        assertTrue(conditionIsVerified);
    }

    private void testAllScoresAreInTheValidRange(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            assertTrue(players[i].getAuctionStance().getScore().getScore() >= IAuctionAction.MIN_AUCTION_SCORE);
            assertTrue(players[i].getAuctionStance().getScore().getScore() <= IAuctionAction.MAX_AUCTION_SCORE);
        }
    }

    private int getIndexOfWinner() {
        Player[] inputOutputPlayers = inputOutputGameTable.getPlayers();
        int winnerIndex = 0;
        for (int i = 0; i < inputOutputPlayers.length; i++) {
            final Player player = inputOutputPlayers[i];
            if (player.isWinner()) {
                winnerIndex = i;
                break;
            }
        }
        return winnerIndex;
    }
}