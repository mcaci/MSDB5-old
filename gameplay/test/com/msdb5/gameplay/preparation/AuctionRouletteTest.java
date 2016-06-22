package com.msdb5.gameplay.preparation;

import com.msdb5.game.factory.table.PreparedGameTableFactory;
import com.msdb5.game.player.Player;
import com.msdb5.game.player.ia.player.*;
import com.msdb5.game.player.info.AuctionScore;
import com.msdb5.game.table.GameTable;
import com.msdb5.game.table.GameTableInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Player[] fakePlayers = {new Ambiguo(), new BuonCompagno(), new CampioneDecaduto(), new Dubbioso(), new Rialzatore()};
        inputOutputGameTable = new PreparedGameTableFactory(true).create(fakePlayers);
        auctionRouletteTest = new AuctionRoulette();
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

        assertTrue(newIndex < GameTableInfo.NUMBER_OF_PLAYERS);
        if (index >= GameTableInfo.NUMBER_OF_PLAYERS) {
            assertTrue(newIndex == ++index % GameTableInfo.NUMBER_OF_PLAYERS);
        } else {
            assertTrue(newIndex == ++index);
        }
    }

    @Test
    public void testExecute() throws Exception {
        // output game table
        auctionRouletteTest.executeOn(inputOutputGameTable);

        // the preparation should be over at the end, all tests are done by preparation round test
        performGlobalVerification(inputOutputGameTable.getPlayers());
        performSpecificLastRoundVerification(inputOutputGameTable.getPlayers());

        // verify the score of the game table
        scoreValidation(inputOutputGameTable.getGameTableInfo().getAuctionScore());
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
            byte iScore = players[i].getAuctionInfo().getAuctionScore().getScore();
            if (iScore > 60) {
                for (int j = i + 1; j < players.length && conditionIsVerified; j++) {
                    byte jScore = players[j].getAuctionInfo().getAuctionScore().getScore();
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
            assertTrue(players[i].getAuctionInfo().getAuctionScore().getScore() >= AuctionScore.MIN_SCORE);
            assertTrue(players[i].getAuctionInfo().getAuctionScore().getScore() <= AuctionScore.MAX_SCORE);
        }
    }

    private void performSpecificLastRoundVerification(Player[] players) {
        int winnerIndex = getIndexOfWinner(players);
        if (winnerIndex < 0) {
            fail("no winner is registered");
        }
        // 1) check there is a winner and four folded
        testWinnerCase(players, winnerIndex);
        // 2) the winner has the greater preparation score
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
        byte winnerScore = players[winnerIndex].getAuctionInfo().getAuctionScore().getScore();
        if (winnerScore > 60) {
            for (int i = 0; i < players.length; i++) {
                if (i != winnerIndex) {
                    final Player player = players[i];
                    assertTrue(player.toString() + " is not the winner so its preparation score should be inferior",
                            winnerScore > player.getAuctionInfo().getAuctionScore().getScore());
                }
            }
        }
    }

    private void scoreValidation(int auctionScore) {
        assertTrue(auctionScore <= AuctionScore.MAX_SCORE);
        assertTrue(auctionScore > AuctionScore.MIN_SCORE);
    }

}