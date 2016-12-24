package com.msdb5.game.mechanics.analysis.card;

import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.cardset.card.CardNumber;
import com.msdb5.game.cardset.card.MockCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 01/01/16.
 */
public abstract class BaseCardEvaluatorTest {

    final static Class[] EVAL_IMPL_CLASSES = {FixedScaleAnalyzer.class};
    ICardAnalyzer iCardAnalyzerTestObject;
    Class<?> implClass;
    Card inputCard = new MockCard();
    int evaluation = 0;

    @Before
    public void setUp() throws Exception {
        Constructor<?> constructor = implClass.getConstructor();
        iCardAnalyzerTestObject = (ICardAnalyzer) constructor.newInstance();
        evaluation = iCardAnalyzerTestObject.analyze(inputCard);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Card Evaluated with " + iCardAnalyzerTestObject.getClass().getSimpleName() + ": " + this.inputCard + ", value: " + evaluation);
    }

    @Test
    public void testValidity() throws Exception {
        assertTrue(evaluation + " is less than 0", evaluation >= 0);
    }

    @Test
    public void testConsistency() throws Exception {
        int secondEvaluation = iCardAnalyzerTestObject.analyze(inputCard);
        assertEquals("Second evaluation <" + secondEvaluation +
                        "> is not the same as the first one <" + evaluation + ">",
                evaluation, secondEvaluation);
    }

    @Test
    public void testWeightConsistency() throws Exception {
        Card secondCard = new MockCard();
        CardNumber first = inputCard.getCardNumber();
        CardNumber second = secondCard.getCardNumber();
        while (first.equals(second)) {
            secondCard = new MockCard();
            second = secondCard.getCardNumber();
        }
        int valueForSecondCard = iCardAnalyzerTestObject.analyze(secondCard);
        if (first.getWeight() > second.getWeight()) {
            assertTrue("Evaluation for card " + inputCard + "that is " +
                            evaluation + " should not be less than " +
                            "Evaluation for card " + secondCard + "that is " +
                            valueForSecondCard,
                    evaluation > valueForSecondCard);
        } else if (first.getWeight() < second.getWeight()) {
            assertTrue("Evaluation for card " + inputCard + "that is " +
                            evaluation + " should not be more than " +
                            "Evaluation for card " + secondCard + "that is " +
                            valueForSecondCard,
                    evaluation < valueForSecondCard);
        } else fail("weights cannot be equal");
    }

}
