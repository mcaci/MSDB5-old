package game.player;

import game.elements.cardset.MockHand;
import game.player.auction.Score;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import strategy.auction.MockAuctionAction;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 04/09/15.
 */
@RunWith(Parameterized.class)
public class MockPlayer extends Player {

    public MockPlayer(boolean isSideDeckPresent) {
        super(new MockHand(isSideDeckPresent), new MockAuctionAction());
    }

    @Parameterized.Parameters
    public static Collection initParameters() {
        return Arrays.asList(new Object[][]{
                {true},
                {false}
        });
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
