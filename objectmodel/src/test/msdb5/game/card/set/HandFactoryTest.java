package msdb5.game.card.set;

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikiforos on 08/09/15.
 */
@RunWith(Parameterized.class)
public class HandFactoryTest extends CardSetFactoryTest {

    private boolean isSideDeckPresent;

    public HandFactoryTest(boolean isSideDeckPresent) {
        super(new HandFactory(isSideDeckPresent));
        this.isSideDeckPresent = isSideDeckPresent;
    }

    @Parameterized.Parameters
    public static Collection inputParameters() {
        return Arrays.asList(new Object[][]{
                {true},
                {false}
        });
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("HandFactoryTest: showing hand after creation");
        super.tearDown();
    }

    @Override
    public void testOnConcreteSize(int deckSize) {
        int expectedDeckSize = getExpectedDeckSize();
        assertEquals("Size doesn't correspond to test value " + expectedDeckSize,
                expectedDeckSize,
                deckSize);
    }

    private int getExpectedDeckSize() {
        int expectedDeckSize = 0;
        if (this.isSideDeckPresent) {
            expectedDeckSize = Hand.WITH_SIDE_DECK_HAND_SIZE;
        } else {
            expectedDeckSize = Hand.WITHOUT_SIDE_DECK_HAND_SIZE;
        }
        return expectedDeckSize;
    }

    public Hand getMockHand() {
        return (Hand) getMockCardSet();
    }
}
