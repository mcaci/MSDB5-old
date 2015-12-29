package game.elements.player;

import game.elements.player.info.auction.Score;
import game.elements.player.strategy.auction.MockUnwaveringAuctionPersonality;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockUnwaveringPlayer extends MockPlayer {

    public MockUnwaveringPlayer(boolean isSideDeckPresent) {
        super(isSideDeckPresent, new MockUnwaveringAuctionPersonality());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("MockPlayer: " + this);
    }

    @Test
    public void testValidity() throws Exception {
        assertNotNull(this.getHand());
        assertFalse(this.getHand().isEmpty());
        assertNotNull(this.getAuctionInfo());
        assertTrue(this.getAuctionInfo().getScore().getScore() >= Score.MIN_SCORE);
        assertTrue(this.getAuctionInfo().getScore().getScore() <= Score.MAX_SCORE);
        assertNotNull(this.getAuctionInfo().getStatus());
    }

}
