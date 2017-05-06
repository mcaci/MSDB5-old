package msdb5.gameplay.pregame;

import msdb5.game.player.Player;
import msdb5.game.table.GameTable;
import msdb5.game.table.GameTableFactory;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by mcaci on 4/12/17.
 */
public class AuctionOverCheckerTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testWithNewGameTable() throws Exception {
        AuctionOverChecker auctionOverChecker = new AuctionOverChecker();
        Player[] fakePlayers = {new TestPlayerForGamePlayer(1), new TestPlayerForGamePlayer(2),
                new TestPlayerForGamePlayer(3), new TestPlayerForGamePlayer(4), new TestPlayerForGamePlayer(5)};
        GameTable gameTable = new GameTableFactory(true).create(fakePlayers);
        boolean test = auctionOverChecker.isAuctionOver(gameTable);
        assertFalse(test);
    }

}