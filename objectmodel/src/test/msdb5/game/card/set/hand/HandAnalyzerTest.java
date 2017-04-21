package msdb5.game.card.set.hand;

import msdb5.game.card.CardSuit;
import msdb5.game.card.set.Hand;
import msdb5.game.card.set.HandFactoryTest;
import msdb5.game.card.set.analysis.HandAnalysisData;
import msdb5.game.card.set.analysis.HandAnalyzer;
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
        analysisData = iHandEvaluatorTestObject.analyze();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Hand evaluated with " + iHandEvaluatorTestObject.getClass().getSimpleName() + ": " + this.iHandEvaluatorTestObject);
    }

    @Test
    public void testDataIsNotNull() throws Exception {
        assertNotNull(analysisData);
    }

    @Test
    public void testCountMapSize() throws Exception {
        int sum = analysisData.getSuitStrengthMap().values().stream().mapToInt(Integer::intValue).sum();
        assertEquals(inputHand.size(), sum);
    }

    @Test
    public void testSuitDensityLowerBound() throws Exception {
        float minValue = inputHand.size() / (float) CardSuit.values().length;
        assertTrue(analysisData.getSuitDensity() >= minValue);
    }

    @Test
    public void testSuitDensityUpperBound() throws Exception {
        float maxValue = inputHand.size();
        assertTrue(analysisData.getSuitDensity() <= maxValue);
    }

    @Test
    public void testWeaknessIndexLowerBound() throws Exception {
        // weakeness index between 1 and 4
        assertTrue(analysisData.getWeaknessIndex() > 0);
    }

    @Test
    public void testWeaknessIndexUpperBound() throws Exception {
        // weakeness index between 1 and 4
        assertTrue(analysisData.getWeaknessIndex() < 5);
    }

    @Test
    public void testDistanceLowerBound() throws Exception {
        // distance >= 0
        assertTrue(analysisData.getDistanceFromSecond() >= 0);
    }
}
