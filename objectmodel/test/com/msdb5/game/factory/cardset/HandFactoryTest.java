package com.msdb5.game.factory.cardset;

import com.msdb5.game.cardset.Hand;
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
        this.mockCardSet = ((HandFactory) this.cardSetFactoryTestObject).createHand();
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
        if (this.isSideDeckPresent) {
            assertEquals("Size doesn't correspond to test value " + Hand.WITH_SIDE_DECK_HAND_SIZE,
                    Hand.WITH_SIDE_DECK_HAND_SIZE,
                    mockCardSet.getCardSet().size());
        } else {
            assertEquals("Size doesn't correspond to test value " + Hand.WITHOUT_SIDE_DECK_HAND_SIZE,
                    Hand.WITHOUT_SIDE_DECK_HAND_SIZE,
                    mockCardSet.getCardSet().size());
        }
    }


    public Hand getMockHand() {
        return (Hand) mockCardSet;
    }
}
