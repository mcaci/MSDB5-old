package game.factory.cardset;

import game.elements.base.Card;
import game.elements.cardset.Hand;
import org.junit.After;
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

    private final Hand mockHand;
    private boolean isSizeDeckPresent;

    public HandFactoryTest(boolean isSizeDeckPresent) {
        super(new HandFactory(isSizeDeckPresent));
        this.isSizeDeckPresent = isSizeDeckPresent;
        this.mockHand = ((HandFactory) cardSetFactoryTestObject).createHand();
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
        return mockHand.getCardSet();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("HandFactoryTest: showing hand after creation");
        System.out.println(mockHand.toString());
    }

    @Test
    public void testGetCreatedHand() throws Exception {
        assertNotNull(mockHand);
        assertNotNull(mockHand.getCardSet());
        if (isSizeDeckPresent) {
            assertEquals("Size doesn't correspond to test value " + Hand.WITH_SIDE_DECK_HAND_SIZE,
                    Hand.WITH_SIDE_DECK_HAND_SIZE,
                    mockHand.getCardSet().size());
        } else {
            assertEquals("Size doesn't correspond to test value " + Hand.WITHOUT_SIDE_DECK_HAND_SIZE,
                    Hand.WITHOUT_SIDE_DECK_HAND_SIZE,
                    mockHand.getCardSet().size());
        }
    }

    public Hand getMockHand() {
        return mockHand;
    }
}
