package msdb5.game.card.set.hand;

import msdb5.game.card.set.Hand;
import msdb5.game.card.CardSuit;
import msdb5.game.card.set.analysis.HandAnalysisData;
import msdb5.game.card.set.analysis.HandAnalyzer;
import msdb5.game.card.set.HandFactoryTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandAnalyzerTest {

    private HandAnalyzer iHandEvaluatorTestObject;

    private Hand inputHand;
    private HandAnalysisData analysisData;

    @Before
    public void setUp() throws Exception {
        inputHand = new HandFactoryTest(true).getMockHand();
        iHandEvaluatorTestObject = new HandAnalyzer(inputHand);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Hand evaluated with " + iHandEvaluatorTestObject.getClass().getSimpleName() + ": " + this.iHandEvaluatorTestObject);
    }

    @Test
    public void testEvaluate() throws Exception {
        iHandEvaluatorTestObject.analyze();
        analysisData = iHandEvaluatorTestObject.analyze();
        assertNotNull(analysisData);
        int sum = 0;
        for (Integer value : analysisData.getSuitStrengthMap().values()) {
            sum += value;
        }
        assertEquals(inputHand.size(), sum);
        float minValue = inputHand.size() / (float) CardSuit.values().length;
        assertTrue(analysisData.getSuitDensity() >= minValue);
        // weakeness index between 1 and 4
        assertTrue(analysisData.getWeaknessIndex() > 0);
        assertTrue(analysisData.getWeaknessIndex() < 5);
        // distance >= 0
        assertTrue(analysisData.getDistanceFromSecond() >= 0);
    }
}
