package msdb5.game.card.set;

import msdb5.game.card.MockCard;
import org.junit.After;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikiforos on 08/09/15.
 */
public class HandFactoryTest extends CardSetFactoryTest {

    public HandFactoryTest() {
        super(new EmptyHandFactory());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("HandFactoryTest: showing hand after creation");
        super.tearDown();
    }

    @Override
    public void testOnConcreteSize(int handSize) {
        int expectedDeckSize = 0;
        assertEquals("Size doesn't correspond to test value " + expectedDeckSize,
                expectedDeckSize,
                handSize);
    }

    public Hand getMockHand(int size) {
        IntStream.range(0, size).forEach(i -> getMockCardSet().add(new MockCard()));
        return (Hand) getMockCardSet();
    }
}
