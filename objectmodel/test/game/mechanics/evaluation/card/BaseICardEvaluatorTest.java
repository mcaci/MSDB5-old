package game.mechanics.evaluation.card;

import game.elements.base.Card;
import game.elements.base.CardNumber;
import game.elements.base.MockCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 01/01/16.
 */
public abstract class BaseICardEvaluatorTest {

    final static Class[] EVAL_IMPL_CLASSES = {DummyCardEvaluator.class, FixedScaleEvaluator.class};
    ICardEvaluator iCardEvaluatorTestObject;
    Class<?> implClass;
    Card inputCard = new MockCard();
    int evaluation = 0;

    @Before
    public void setUp() throws Exception {
        Constructor<?> constructor = implClass.getConstructor();
        iCardEvaluatorTestObject = (ICardEvaluator) constructor.newInstance();
        evaluation = iCardEvaluatorTestObject.evaluateCard(inputCard);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Card Evaluated with " + iCardEvaluatorTestObject.getClass().getSimpleName() + ": " + this.inputCard + ", value: " + evaluation);
    }

    @Test
    public void testValidity() throws Exception {
        assertTrue(evaluation + " is less than 0", evaluation >= 0);
    }

    @Test
    public void testConsistency() throws Exception {
        int secondEvaluation = iCardEvaluatorTestObject.evaluateCard(inputCard);
        assertEquals("Second evaluation <" + secondEvaluation +
                        "> is not the same as the first one <" + evaluation + ">",
                evaluation, secondEvaluation);
    }

    @Test
    public void testWeightConsistency() throws Exception {
        Card secondCard = new MockCard();
        CardNumber first = inputCard.getCardNumber();
        CardNumber second = secondCard.getCardNumber();
//        System.out.println("Second card is: " + secondCard); // TODO: to replace with logs
        while (first.equals(second)) {

//            System.out.println("Second card is: " + secondCard); // TODO: to replace with logs
            secondCard = new MockCard();
            second = secondCard.getCardNumber();
        }
        int valueForSecondCard = iCardEvaluatorTestObject.evaluateCard(secondCard);
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
