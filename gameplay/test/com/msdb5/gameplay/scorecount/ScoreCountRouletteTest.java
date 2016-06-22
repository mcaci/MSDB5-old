package com.msdb5.gameplay.scorecount;

import com.msdb5.game.cardset.Hand;
import com.msdb5.game.factory.table.PreparedGameTableFactory;
import com.msdb5.game.player.Player;
import com.msdb5.game.player.ia.player.*;
import com.msdb5.game.table.GameTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by nikiforos on 18/02/16.
 */
public class ScoreCountRouletteTest {

//    private ScoreCountRoulette scoreCountRouletteTest;
    private GameTable inputOutputGameTable;

    @Before
    public void setUp() throws Exception {
        Player[] fakePlayers = {new Ambiguo(), new BuonCompagno(), new CampioneDecaduto(), new Dubbioso(), new Rialzatore()};
        inputOutputGameTable = new PreparedGameTableFactory(true).create(fakePlayers);
        ScoreCountRoulette scoreCountRouletteTest = new ScoreCountRoulette();
        scoreCountRouletteTest.executeOn(inputOutputGameTable);

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("scoreCountRouletteTest: gameTable after the score count");
        System.out.println(inputOutputGameTable);
    }

    @Test
    public void testSinglePlayersScore() throws Exception {
        Player[] players = inputOutputGameTable.getPlayers();
        for (Player player: players) {
            assertTrue(player.tellScore() >= 0);
            assertTrue(player.tellScore() <= 120);
        }
    }

    @Test
    public void testOverallScore() throws Exception {
        Player[] players = inputOutputGameTable.getPlayers();
        byte overallScore = 0;
        for (Player player: players) {
            overallScore += player.tellScore();
        }
        assertEquals(120, overallScore);
    }

    @Test
    public void testScoreBeingSumOfValues() throws Exception {
        Player[] players = inputOutputGameTable.getPlayers();
        byte overallScore = 0;
        Hand cardPile = players[0].getHand();
        fail("test not implemented yet");
    }
}