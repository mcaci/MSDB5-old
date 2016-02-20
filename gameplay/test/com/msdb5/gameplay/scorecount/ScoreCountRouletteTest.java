package com.msdb5.gameplay.scorecount;

import com.msdb5.game.factory.table.PreparedGameTableFactory;
import com.msdb5.game.player.Player;
import com.msdb5.game.player.ia.player.*;
import com.msdb5.game.table.GameTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by nikiforos on 18/02/16.
 */
public class ScoreCountRouletteTest {

    private ScoreCountRoulette scoreCountRouletteTest;
    private GameTable inputOutputGameTable;

    @Before
    public void setUp() throws Exception {
        Player[] fakePlayers = {new Ambiguo(), new BuonCompagno(), new CampioneDecaduto(), new Dubbioso(), new Rialzatore()};
        inputOutputGameTable = new PreparedGameTableFactory(true).create(fakePlayers);
        scoreCountRouletteTest = new ScoreCountRoulette();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testExecute() throws Exception {
        scoreCountRouletteTest.execute(inputOutputGameTable);

    }
}