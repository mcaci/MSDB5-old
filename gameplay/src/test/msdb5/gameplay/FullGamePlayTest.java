package msdb5.gameplay;

import msdb5.game.player.Player;
import msdb5.game.table.GameTable;
import msdb5.game.table.GameTableFactory;
import msdb5.gameplay.endgame.ScoreCountRoulette;
import msdb5.gameplay.ingame.SimpleIngameRoulette;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import msdb5.gameplay.pregame.AuctionRoulette;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 08/02/16.
 */
public class FullGamePlayTest {

    private GameTable inputOutputGameTable;

    @Before
    public void setUp() throws Exception {
        Player[] testPlayers = {new TestPlayerForGamePlayer(1), new TestPlayerForGamePlayer(2), new TestPlayerForGamePlayer(3), new TestPlayerForGamePlayer(4), new TestPlayerForGamePlayer(5)};
        inputOutputGameTable = new GameTableFactory(false).create(testPlayers);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(inputOutputGameTable);
    }

    @Test
    public void execute() throws Exception {
        GameRoulette gameRoulette;

        // step1
        gameRoulette = new AuctionRoulette();
        gameRoulette.executeOn(inputOutputGameTable);

        // step2
        gameRoulette = new SimpleIngameRoulette();
        gameRoulette.executeOn(inputOutputGameTable);

        // step3
        gameRoulette = new ScoreCountRoulette();
        gameRoulette.executeOn(inputOutputGameTable);

        assertNotNull(inputOutputGameTable);

    }

}
