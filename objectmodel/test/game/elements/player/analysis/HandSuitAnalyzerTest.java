package game.elements.player.analysis;

import game.elements.cardset.Hand;
import game.elements.cardset.MockHand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandSuitAnalyzerTest {

    private HandSuitAnalyzer iHandEvaluatorTestObject;

    private Hand inputHand;
    private float[] briscolaEvaluation;

    @Before
    public void setUp() throws Exception {
        inputHand = new MockHand(true);
        iHandEvaluatorTestObject = new HandSuitAnalyzer(inputHand);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Hand evaluated with " + iHandEvaluatorTestObject.getClass().getSimpleName() + ": " + this.iHandEvaluatorTestObject);
    }

    @Test
    public void testEvaluate() throws Exception {
        iHandEvaluatorTestObject.evaluate();
        briscolaEvaluation = iHandEvaluatorTestObject.getSuitEvaluation();
        float sum = 0F;
        for (int i = 0; i < briscolaEvaluation.length; i++) {
            sum += briscolaEvaluation[i];
        }
        assertEquals(1.0, sum, 0.1);
    }
}
