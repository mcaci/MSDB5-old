package game.mechanics.analysis;

import game.elements.base.CardSuit;
import game.elements.cardset.Hand;
import game.factory.cardset.HandFactoryTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandSuitAnalyzerTest {

    private HandSuitAnalyzer iHandEvaluatorTestObject;

    private Hand inputHand;
    private Map<CardSuit, Float> briscolaEvaluationResult;

    @Before
    public void setUp() throws Exception {
        inputHand = new HandFactoryTest(true).getMockHand();
        iHandEvaluatorTestObject = new HandSuitAnalyzer(inputHand);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Hand evaluated with " + iHandEvaluatorTestObject.getClass().getSimpleName() + ": " + this.iHandEvaluatorTestObject);
    }

    @Test
    public void testEvaluate() throws Exception {
        iHandEvaluatorTestObject.analyze();
        briscolaEvaluationResult = iHandEvaluatorTestObject.analyze();
        float sum = 0F;
        for (Map.Entry<CardSuit, Float> aBriscolaEvaluation : briscolaEvaluationResult.entrySet()) {
            sum += aBriscolaEvaluation.getValue();
        }
        assertEquals(1.0, sum, 0.1);
    }
}
