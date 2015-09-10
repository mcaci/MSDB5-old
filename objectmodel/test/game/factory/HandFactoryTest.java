package game.factory;

import game.elements.Card;
import game.player.Hand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 08/09/15.
 */
@RunWith(Parameterized.class)
public class HandFactoryTest extends CardSetFactoryTest {

    private boolean isSizeDeckPresent;
    private Hand handCreated;

    public HandFactoryTest(boolean isSizeDeckPresent) {
        this.isSizeDeckPresent = isSizeDeckPresent;
    }

    @Parameterized.Parameters
    public static Collection inputParameters() {
        return Arrays.asList(new Object[][]{
                {true},
                {false}
        });
    }

    @Override
    public Collection<Card> getCardSet() {
        return handCreated.getHand();
    }

    @Before
    public void setUp() throws Exception {
        cardSetFactoryTestObject = new HandFactory(isSizeDeckPresent);
        handCreated = ((HandFactory) cardSetFactoryTestObject).createHand();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("HandFactoryTest: showing hand after creation");
        System.out.println(handCreated.toString());
    }

    @Test
    public void testGetCreatedHand() throws Exception {
        assertNotNull(handCreated);
        assertNotNull(handCreated.getHand());
        if (isSizeDeckPresent) {
            assertEquals("Size doesn't correspond to test value " + Hand.WITH_SIDE_DECK_HAND_SIZE,
                    Hand.WITH_SIDE_DECK_HAND_SIZE,
                    handCreated.getHand().size());
        } else {
            assertEquals("Size doesn't correspond to test value " + Hand.WITHOUT_SIDE_DECK_HAND_SIZE,
                    Hand.WITHOUT_SIDE_DECK_HAND_SIZE,
                    handCreated.getHand().size());
        }
    }
}
