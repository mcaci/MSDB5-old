package msdb5.game.card.set.hand;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;
import msdb5.game.card.set.Hand;
import msdb5.game.card.set.HandFactoryTest;
import msdb5.game.card.set.analysis.HandAnalysisData;
import msdb5.game.card.set.analysis.HandAnalyzer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    public void testDataIsNotNull() throws Exception {
        analysisData = iHandEvaluatorTestObject.analyze();
        assertNotNull(analysisData);
    }

    @Test
    public void testCountMapSize() throws Exception {
        analysisData = iHandEvaluatorTestObject.analyze();
        int sum = analysisData.getSuitStrengthMap().values().stream().mapToInt(value -> value).sum();
        assertEquals(inputHand.size(), sum);
    }

    @Test
    public void testSuitDensityLowerBound() throws Exception {
        analysisData = iHandEvaluatorTestObject.analyze();
        float minValue = inputHand.size() / (float) CardSuit.values().length;
        assertTrue(analysisData.getSuitDensity() >= minValue);
    }

    @Test
    public void testSuitDensityUpperBound() throws Exception {
        analysisData = iHandEvaluatorTestObject.analyze();
        float maxValue = inputHand.size();
        assertTrue(analysisData.getSuitDensity() <= maxValue);
    }

    @Test
    public void testWeaknessIndexLowerBound() throws Exception {
        analysisData = iHandEvaluatorTestObject.analyze();
        // weakeness index between 1 and 4
        assertTrue(analysisData.getWeaknessIndex() > 0);
    }

    @Test
    public void testWeaknessIndexUpperBound() throws Exception {
        analysisData = iHandEvaluatorTestObject.analyze();
        // weakeness index between 1 and 4
        assertTrue(analysisData.getWeaknessIndex() < 5);
    }

    @Test
    public void testDistanceLowerBound() throws Exception {
        analysisData = iHandEvaluatorTestObject.analyze();
        // distance >= 0
        assertTrue(analysisData.getDistanceFromSecond() >= 0);
    }

    @Ignore
    @Test
    public void testDistanceOf0() throws Exception {
        inputHand.getCardSet().clear();
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.RE, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.CAVALLO, CardSuit.SPADE));
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.RE, CardSuit.COPPE));
        iHandEvaluatorTestObject = new HandAnalyzer(inputHand);
        analysisData = iHandEvaluatorTestObject.analyze();
        assertEquals(0, analysisData.getDistanceFromSecond());
    }

    @Test
    public void testDistanceOf1() throws Exception {
        inputHand.getCardSet().clear();
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.RE, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.CAVALLO, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.RE, CardSuit.COPPE));
        iHandEvaluatorTestObject = new HandAnalyzer(inputHand);
        analysisData = iHandEvaluatorTestObject.analyze();
        assertEquals(1, analysisData.getDistanceFromSecond());
    }

    @Test
    public void testDistanceOf2() throws Exception {
        inputHand.getCardSet().clear();
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.BASTONI));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.RE, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.CAVALLO, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.RE, CardSuit.COPPE));
        iHandEvaluatorTestObject = new HandAnalyzer(inputHand);
        analysisData = iHandEvaluatorTestObject.analyze();
        assertEquals(2, analysisData.getDistanceFromSecond());
    }

    @Ignore
    @Test
    public void testDistanceOf3() throws Exception {
        inputHand.getCardSet().clear();
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.BASTONI));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.RE, CardSuit.SPADE));
        inputHand.add(new Card(CardNumber.CAVALLO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.RE, CardSuit.COPPE));
        iHandEvaluatorTestObject = new HandAnalyzer(inputHand);
        analysisData = iHandEvaluatorTestObject.analyze();
        assertEquals(3, analysisData.getDistanceFromSecond());
    }

    @Ignore
    @Test
    public void testDistanceOf4() throws Exception {
        inputHand.getCardSet().clear();
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.BASTONI));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.ORO));
        inputHand.add(new Card(CardNumber.DONNA, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.CAVALLO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.RE, CardSuit.COPPE));
        iHandEvaluatorTestObject = new HandAnalyzer(inputHand);
        analysisData = iHandEvaluatorTestObject.analyze();
        assertEquals(4, analysisData.getDistanceFromSecond());
    }

    @Test
    public void testDistanceOf5() throws Exception {
        inputHand.getCardSet().clear();
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.BASTONI));
        inputHand.add(new Card(CardNumber.SETTE, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.DONNA, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.CAVALLO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.RE, CardSuit.COPPE));
        iHandEvaluatorTestObject = new HandAnalyzer(inputHand);
        analysisData = iHandEvaluatorTestObject.analyze();
        assertEquals(5, analysisData.getDistanceFromSecond());
    }

    @Test
    public void testDistanceOf7() throws Exception {
        inputHand.getCardSet().clear();
        inputHand.add(new Card(CardNumber.SEI, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.SETTE, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.DONNA, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.CAVALLO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.ASSO, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.TRE, CardSuit.COPPE));
        inputHand.add(new Card(CardNumber.RE, CardSuit.COPPE));
        iHandEvaluatorTestObject = new HandAnalyzer(inputHand);
        analysisData = iHandEvaluatorTestObject.analyze();
        assertEquals(7, analysisData.getDistanceFromSecond());
    }
}
