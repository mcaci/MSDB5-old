package game.player;

import gameinfo.GameInfoConstants;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 04/09/15.
 */
public abstract class MockPlayer extends Player {

    @After
    public void tearDown() throws Exception {
        System.out.println("MockPlayer: " + this);
    }

    @Test
    public void testValidity() throws Exception {
        assertNotNull(this.getHand());
        assertFalse(this.getHand().isEmpty());
        assertNotNull(this.getPersonalAuctionInfo());
        assertTrue(this.getPersonalAuctionInfo().getAuctionScore().getScore() >= GameInfoConstants.MIN_AUCTION_SCORE);
        assertTrue(this.getPersonalAuctionInfo().getAuctionScore().getScore() <= GameInfoConstants.MAX_AUCTION_SCORE);
        assertNotNull(this.getPersonalAuctionInfo().getAuctionPlayerStance());
    }
}
