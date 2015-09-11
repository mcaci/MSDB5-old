package game.elements;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 11/09/15.
 */
@RunWith(Parameterized.class)
public class CardSuitTest {

    private CardSuit cardSuitTestObject;

    public CardSuitTest(CardSuit cardSuitTestObject) {
        this.cardSuitTestObject = cardSuitTestObject;
    }

    @Parameterized.Parameters
    public static Collection inputParameters() {
        CardSuit[] cardSuits = CardSuit.values();
        Object[][] inputParams = new Object[cardSuits.length][];
        for (int i = 0; i < inputParams.length; i++) {
            inputParams[i] = new Object[1];
            inputParams[i][0] = cardSuits[i];
        }
        return Arrays.asList(inputParams);
    }

    @Test
    public void testSetAsBriscola() throws Exception {
        this.cardSuitTestObject.setAsBriscola();
        CardSuit[] cardSuits = CardSuit.values();
        for (int i = 0; i < cardSuits.length; i++) {
            if (this.cardSuitTestObject == cardSuits[i]) {
                assertTrue(cardSuits[i].isBriscola());
            } else {
                assertFalse(cardSuits[i].isBriscola());
            }
        }
    }

    @Test
    public void testResetBriscola() throws Exception {
        this.cardSuitTestObject.resetBriscola();
        CardSuit[] cardSuits = CardSuit.values();
        for (int i = 0; i < cardSuits.length; i++) {
            assertFalse(cardSuits[i].isBriscola());
        }
    }
}