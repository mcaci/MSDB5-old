package gameplay;

import game.factory.table.PreparedGameTableFactoryTest;
import game.player.Player;
import game.table.GameTable;
import gameplay.auction.AuctionRoulette;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 08/02/16.
 */
public class FullGamePlayTest {

    private AuctionRoulette auctionRouletteTest;
    private GameTable inputOutputGameTable;

    @Before
    public void setUp() throws Exception {
        auctionRouletteTest = new AuctionRoulette();
        inputOutputGameTable = new PreparedGameTableFactoryTest(true).getMockGameTable();
    }

    @Test
    public void fullGamePlayTest() {
        auctionRouletteTest.execute(inputOutputGameTable);

        Player auctionWinner = inputOutputGameTable.getInfo().getAuctionWinner();
        assertNotNull(auctionWinner);
        System.out.println(auctionWinner);
    }
}
