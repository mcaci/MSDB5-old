package com.msdb5.gameplay.scorecount;

import com.msdb5.game.factory.table.PreparedGameTableFactoryTest;
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
        scoreCountRouletteTest = new ScoreCountRoulette();
        inputOutputGameTable = new PreparedGameTableFactoryTest(true).getMockGameTable();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testExecute() throws Exception {
        scoreCountRouletteTest.execute(inputOutputGameTable);

    }
}