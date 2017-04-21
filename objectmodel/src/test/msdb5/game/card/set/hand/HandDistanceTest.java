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
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandDistanceTest {

    private HandAnalyzer iHandEvaluatorTestObject;

    private Hand inputHand;
    private HandAnalysisData analysisData;

    @Before
    public void setUp() throws Exception {
        inputHand = new HandFactoryTest(true).getMockHand();
        inputHand.getCardSet().clear();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Hand evaluated with " + iHandEvaluatorTestObject.getClass().getSimpleName() + ": " + this.iHandEvaluatorTestObject);
    }

    private void addCardsToMockHand(Card... cards) {
        for (Card card : cards) { inputHand.add(card); }
    }

    private void performAnalysis(int expectedDistance) {
        iHandEvaluatorTestObject = new HandAnalyzer(inputHand);
        analysisData = iHandEvaluatorTestObject.analyze();
        assertEquals(expectedDistance, analysisData.getDistanceFromSecond());
    }

    @Test
    public void testDistanceOf0() throws Exception {
        addCardsToMockHand(
                new Card(CardNumber.SEI, CardSuit.SPADE),
                new Card(CardNumber.SETTE, CardSuit.COPPE),
                new Card(CardNumber.DONNA, CardSuit.COPPE),
                new Card(CardNumber.CAVALLO, CardSuit.COPPE),
                new Card(CardNumber.ASSO, CardSuit.ORO),
                new Card(CardNumber.TRE, CardSuit.ORO),
                new Card(CardNumber.RE, CardSuit.ORO)
        );
        performAnalysis(0);
    }

    @Test
    public void testDistanceOf1() throws Exception {
        addCardsToMockHand(
                new Card(CardNumber.SEI, CardSuit.COPPE),
                new Card(CardNumber.SETTE, CardSuit.COPPE),
                new Card(CardNumber.DONNA, CardSuit.COPPE),
                new Card(CardNumber.CAVALLO, CardSuit.COPPE),
                new Card(CardNumber.ASSO, CardSuit.ORO),
                new Card(CardNumber.TRE, CardSuit.ORO),
                new Card(CardNumber.RE, CardSuit.ORO)
        );
        performAnalysis(1);
    }

    @Test
    public void testDistanceOf2() throws Exception {
        addCardsToMockHand(
                new Card(CardNumber.SEI, CardSuit.ORO),
                new Card(CardNumber.SETTE, CardSuit.SPADE),
                new Card(CardNumber.DONNA, CardSuit.SPADE),
                new Card(CardNumber.CAVALLO, CardSuit.COPPE),
                new Card(CardNumber.ASSO, CardSuit.COPPE),
                new Card(CardNumber.TRE, CardSuit.COPPE),
                new Card(CardNumber.RE, CardSuit.COPPE)
        );
        performAnalysis(2);
    }

    @Test
    public void testDistanceOf3() throws Exception {
        addCardsToMockHand(
                new Card(CardNumber.SEI, CardSuit.ORO),
                new Card(CardNumber.SETTE, CardSuit.SPADE),
                new Card(CardNumber.DONNA, CardSuit.BASTONI),
                new Card(CardNumber.CAVALLO, CardSuit.COPPE),
                new Card(CardNumber.ASSO, CardSuit.COPPE),
                new Card(CardNumber.TRE, CardSuit.COPPE),
                new Card(CardNumber.RE, CardSuit.COPPE)
        );
        performAnalysis(3);
    }

    @Test
    public void testDistanceOf4() throws Exception {
        addCardsToMockHand(
                new Card(CardNumber.SEI, CardSuit.ORO),
                new Card(CardNumber.SETTE, CardSuit.SPADE),
                new Card(CardNumber.DONNA, CardSuit.COPPE),
                new Card(CardNumber.CAVALLO, CardSuit.COPPE),
                new Card(CardNumber.ASSO, CardSuit.COPPE),
                new Card(CardNumber.TRE, CardSuit.COPPE),
                new Card(CardNumber.RE, CardSuit.COPPE)
        );
        performAnalysis(4);
    }

    @Test
    public void testDistanceOf5() throws Exception {
        addCardsToMockHand(
                new Card(CardNumber.SEI, CardSuit.ORO),
                new Card(CardNumber.SETTE, CardSuit.COPPE),
                new Card(CardNumber.DONNA, CardSuit.COPPE),
                new Card(CardNumber.CAVALLO, CardSuit.COPPE),
                new Card(CardNumber.ASSO, CardSuit.COPPE),
                new Card(CardNumber.TRE, CardSuit.COPPE),
                new Card(CardNumber.RE, CardSuit.COPPE)
        );
        performAnalysis(5);
    }

    @Test
    public void testDistanceOf7() throws Exception {
        addCardsToMockHand(
                new Card(CardNumber.SEI, CardSuit.COPPE),
                new Card(CardNumber.SETTE, CardSuit.COPPE),
                new Card(CardNumber.DONNA, CardSuit.COPPE),
                new Card(CardNumber.CAVALLO, CardSuit.COPPE),
                new Card(CardNumber.ASSO, CardSuit.COPPE),
                new Card(CardNumber.TRE, CardSuit.COPPE),
                new Card(CardNumber.RE, CardSuit.COPPE)
        );
        performAnalysis(7);
    }
}
