package gameplay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import player.Player;
import table.GameTableFactory;

import java.util.Arrays;

import static org.junit.Assert.fail;

/**
 * Created by nikiforos on 01/09/15.
 */
public class AuctionRouletteTest {

    private AuctionRoulette auctionRouletteTest;
    private Player[] inputOutputPlayers;

    @Before
    public void setUp() throws Exception {
        auctionRouletteTest = new AuctionRoulette();

        // input players
        GameTableFactory gameTableFactory = new GameTableFactory();
        inputOutputPlayers = gameTableFactory.getCreatedGameTable().getPlayers();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("AuctionRouletteTest: players after the auction");
        System.out.println(Arrays.toString(inputOutputPlayers));
    }

    @Test
    public void testPerformAuction() throws Exception {
        // output game table
        auctionRouletteTest.performAuctionRoulette(inputOutputPlayers);

        // TODO: incomplete test
        fail();
        // assertTrue("all players should have cast their auction", true);
    }


}