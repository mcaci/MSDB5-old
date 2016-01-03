package game.cardset.card;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tested class unused
 * Created by nikiforos on 11/09/15.
 */
@Deprecated
@RunWith(Parameterized.class)
public class EnhancedCardSuitTest {

    private EnhancedCardSuit cardSuitTestObject;

    public EnhancedCardSuitTest(EnhancedCardSuit cardSuitTestObject) {
        this.cardSuitTestObject = cardSuitTestObject;
    }

    @Parameterized.Parameters
    public static Collection inputParameters() {
        EnhancedCardSuit[] cardSuits = EnhancedCardSuit.values();
        Object[][] inputParams = new Object[cardSuits.length][];
        for (int i = 0; i < inputParams.length; i++) {
            inputParams[i] = new Object[1];
            inputParams[i][0] = cardSuits[i];
        }
        return Arrays.asList(inputParams);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("MockCardSuit: " + this.cardSuitTestObject);
    }

    @Test
    public void testSetAsBriscola() throws Exception {
        this.cardSuitTestObject.setAsBriscola();
        EnhancedCardSuit[] cardSuits = EnhancedCardSuit.values();
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
        EnhancedCardSuit[] cardSuits = EnhancedCardSuit.values();
        for (int i = 0; i < cardSuits.length; i++) {
            assertFalse(cardSuits[i].isBriscola());
        }
    }
}