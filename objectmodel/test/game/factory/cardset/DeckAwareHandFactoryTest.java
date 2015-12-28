package game.factory.cardset;

import game.elements.base.Card;
import game.elements.cardset.DeckAwareHand;
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
public class DeckAwareHandFactoryTest extends CardSetFactoryTest {

    private boolean isSizeDeckPresent;
    private DeckAwareHand deckAwareHandCreated;

    public DeckAwareHandFactoryTest(boolean isSizeDeckPresent) {
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
        return deckAwareHandCreated.getCardSet();
    }

    @Before
    public void setUp() throws Exception {
        cardSetFactoryTestObject = new HandFactory(isSizeDeckPresent);
        deckAwareHandCreated = ((HandFactory) cardSetFactoryTestObject).createHand();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("DeckAwareHandFactoryTest: showing hand after creation");
        System.out.println(deckAwareHandCreated.toString());
    }

    @Test
    public void testGetCreatedHand() throws Exception {
        assertNotNull(deckAwareHandCreated);
        assertNotNull(deckAwareHandCreated.getCardSet());
        if (isSizeDeckPresent) {
            assertEquals("Size doesn't correspond to test value " + DeckAwareHand.WITH_SIDE_DECK_HAND_SIZE,
                    DeckAwareHand.WITH_SIDE_DECK_HAND_SIZE,
                    deckAwareHandCreated.getCardSet().size());
        } else {
            assertEquals("Size doesn't correspond to test value " + DeckAwareHand.WITHOUT_SIDE_DECK_HAND_SIZE,
                    DeckAwareHand.WITHOUT_SIDE_DECK_HAND_SIZE,
                    deckAwareHandCreated.getCardSet().size());
        }
    }
}
