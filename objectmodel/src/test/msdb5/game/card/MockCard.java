package msdb5.game.card;

import org.junit.After;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 07/09/15.
 */
public class MockCard extends Card {

    public MockCard() {
        super(CardNumber.values()[new Random().nextInt(CardNumber.values().length)],
                CardSuit.values()[new Random().nextInt(CardSuit.values().length)]);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("MockCard: " + this);
    }

    @Test
    public void testValidity() throws Exception {
        assertTrue(this.isValid());
    }
}
