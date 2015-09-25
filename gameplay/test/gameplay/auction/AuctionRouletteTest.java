package gameplay.auction;

import game.player.MockUnwaveringPlayer;
import game.player.Player;
import game.table.GameTable;
import game.table.MockGameTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import strategy.auction.IAuctionAction;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 01/09/15.
 */
public class AuctionRouletteTest {

    private AuctionRoulette auctionRouletteTest;
    private GameTable inputOutputGameTable;

    @Before
    public void setUp() throws Exception {
        auctionRouletteTest = new AuctionRoulette();
        inputOutputGameTable = new MockGameTable(true);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("auctionRouletteTest: gameTable after the evaluation");
        System.out.println(inputOutputGameTable);
    }

    @Test
    public void testIsAuctionOver() throws Exception {
        Method method = AuctionRoulette.class.getDeclaredMethod("isAuctionOver", GameTable.class);
        method.setAccessible(true);
        Boolean isAuctionOver = (Boolean) method.invoke(auctionRouletteTest, inputOutputGameTable);

        final boolean auctionOverCondition = testWinnerCase(inputOutputGameTable.getPlayers());
        if (auctionOverCondition) {
            assertTrue(isAuctionOver);
        } else {
            assertFalse(isAuctionOver);
        }
    }

    @Test
    public void testSetNextPlayerToGo() throws Exception {
        Method method = AuctionRoulette.class.getDeclaredMethod("setNextPlayerToGo", int.class);
        method.setAccessible(true);
        int index = 0;
        int newIndex = (int) method.invoke(auctionRouletteTest, index);

        assertTrue(newIndex < GameTable.NUMBER_OF_PLAYERS);
        if (index >= GameTable.NUMBER_OF_PLAYERS) {
            assertTrue(newIndex == ++index % GameTable.NUMBER_OF_PLAYERS);
        } else {
            assertTrue(newIndex == ++index);
        }
    }

    @Test
    public void testPerformAuctionRoulette() throws Exception {
        // output game table
        auctionRouletteTest.execute(inputOutputGameTable);

        // the auction should be over at the end, all tests are done by auction round test
        performGlobalVerification(inputOutputGameTable.getPlayers());
        performSpecificLastRoundVerification(inputOutputGameTable.getPlayers());

        // verify the score of the game table
        int auctionScore = inputOutputGameTable.getAuctionScore();
        assertTrue(auctionScore <= IAuctionAction.MAX_AUCTION_SCORE);
        assertTrue(auctionScore >= IAuctionAction.MIN_AUCTION_SCORE);
    }

    @Test
    public void testPerformAuctionRoulette_WithUnwaveringPlayer() throws Exception {
        Player[] players = inputOutputGameTable.getPlayers();
        players[0] = new MockUnwaveringPlayer(true);
        players[1] = new MockUnwaveringPlayer(true);
        players[2] = new MockUnwaveringPlayer(true);
        players[3] = new MockUnwaveringPlayer(true);
        players[4] = new MockUnwaveringPlayer(true);
        testPerformAuctionRoulette();
    }

    private boolean testWinnerCase(Player[] players) {
        int winnerIndex = getIndexOfWinner(players);
        boolean isThereOneWinnerFourFolded = true;
        for (int i = 0; i < players.length; i++) {


            if (i != winnerIndex) {
                final Player player = players[i];
                isThereOneWinnerFourFolded &= player.hasFolded();
            }
        }
        return isThereOneWinnerFourFolded;
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

    private void performGlobalVerification(Player[] players) {
        // 1) all the players scores are different (excpet if score is 60)
        testAllScoresAreDifferent(players);
        // 2) all the players scores are between the valid range
        testAllScoresAreInTheValidRange(players);
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

    private void performSpecificLastRoundVerification(Player[] players) {
        int winnerIndex = getIndexOfWinner(players);
        if (winnerIndex < 0) {
            fail("no winner is registered");
        }
        // 1) check there is a winner and four folded
        testWinnerCase(players, winnerIndex);
        // 2) the winner has the greater auction score
        testWinnerScore(players, winnerIndex);
    }

    private void testWinnerCase(Player[] players, int winnerIndex) {
        for (int i = 0; i < players.length; i++) {
            if (i != winnerIndex) {
                final Player player = players[i];
                assertTrue(player.toString() + " is not the winner (Player: " + i + ") and should not result as one",
                        !player.isWinner());
            }
        }
    }

    private void testWinnerScore(Player[] players, int winnerIndex) {
        byte winnerScore = players[winnerIndex].getAuctionStance().getScore().getScore();
        if (winnerScore > 60) {
            for (int i = 0; i < players.length; i++) {
                if (i != winnerIndex) {
                    final Player player = players[i];
                    assertTrue(player.toString() + " is not the winner so its auction score should be inferior",
                            winnerScore > player.getAuctionStance().getScore().getScore());
                }
            }
        }
    }

}