package game.player;

import game.player.auction.Score;
import org.junit.After;
import org.junit.Test;
import strategy.auction.AuctionActionDefault;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 04/09/15.
 */
public abstract class MockPlayer extends Player {

    public MockPlayer() {
        super(new AuctionActionDefault());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("MockPlayer: " + this);
    }

    @Test
    public void testValidity() throws Exception {
        assertNotNull(this.getHand());
        assertFalse(this.getHand().isEmpty());
        assertNotNull(this.getAuctionStance());
        assertTrue(this.getAuctionStance().getScore().getScore() >= Score.MIN_SCORE);
        assertTrue(this.getAuctionStance().getScore().getScore() <= Score.MAX_SCORE);
        assertNotNull(this.getAuctionStance().getStatus());
    }

}
