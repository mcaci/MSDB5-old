package gameplay.auction;

import game.player.Player;
import game.table.GameTable;
import game.table.MockGameTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import strategy.auction.IAuctionAction;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * TODO: to be deleted and replaced by AuctionRoulette soon
 * Created by nikiforos on 01/09/15.
 */
@Deprecated
public class AuctionRoundTest {

    private AuctionRound auctionRoundTest;
    private GameTable inputOutputGameTable;

    @Before
    public void setUp() throws Exception {
        auctionRoundTest = new AuctionRound();
        inputOutputGameTable = new MockGameTable(true);
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
        auctionRoundTest.execute(inputOutputGameTable);

        // 1) the score of the table is greater than the original one
        int outputScore = inputOutputGameTable.getAuctionScore();
        boolean isCurrentScoreGreaterThanOriginal = outputScore > originalScore;
        if (isCurrentScoreGreaterThanOriginal) {
            // 1.1) all players have acted
            testAllPlayersHaveActed(inputOutputGameTable.getPlayers());
        } else {
            // 1.1) Specific last round checks
            performSpecificLastRoundVerification(inputOutputGameTable.getPlayers());
        }
        // 2) other global checks
        performGlobalVerification(inputOutputGameTable.getPlayers());
    }

    public void performGlobalVerification(Player[] players) {
        // 1) all the players scores are different (excpet if score is 60)
        testAllScoresAreDifferent(players);
        // 2) all the players scores are between the valid range
        testAllScoresAreInTheValidRange(players);
    }

    public void performSpecificLastRoundVerification(Player[] players) {
        int winnerIndex = getIndexOfWinner(players);
        if (winnerIndex < 0) {
            fail("no winner is registered");
        }
        // 1) check there is a winner and four folded
        testWinnerCase(players, winnerIndex);
        // 2) the winner has the greater auction score
        testWinnerScore(players, winnerIndex);
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
                assertTrue(player.toString() + " is not the winner (Player: " + i + ") so it should result as folded",
                        player.hasFolded());
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
            if (iScore > 60) {
                for (int j = i + 1; j < players.length && conditionIsVerified; j++) {
                    byte jScore = players[j].getAuctionStance().getScore().getScore();
                    if (jScore > 60) {
                        conditionIsVerified &= iScore != jScore;
                    }
                }
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

    private int getIndexOfWinner(Player[] players) {
        Player[] inputOutputPlayers = players;
        int winnerIndex = -1;
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