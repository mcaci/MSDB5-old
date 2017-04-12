package msdb5.gameplay.pregame;

import msdb5.game.player.Player;
import msdb5.game.table.GameTable;
import msdb5.game.table.PreparedGameTableFactory;
import msdb5.gameplay.player.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        Player[] fakePlayers = {new Ambiguo(), new BuonCompagno(), new CampioneDecaduto(), new Dubbioso(), new Rialzatore()};
        GameTable gameTable = new PreparedGameTableFactory(true).create(fakePlayers);
        boolean test = auctionOverChecker.isAuctionOver(gameTable);
        assertFalse(test);
    }

}