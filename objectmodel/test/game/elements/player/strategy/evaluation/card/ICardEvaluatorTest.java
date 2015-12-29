package game.elements.player.strategy.evaluation.card;

import game.elements.base.Card;
import game.elements.base.CardNumber;
import game.elements.base.MockCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class ICardEvaluatorTest {

    private ICardEvaluator iCardEvaluatorTestObject;

    private Class<?> implClass;
    private Card inputCard;
    private int evaluation = 0;

    public ICardEvaluatorTest(Class<?> implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection<?> primeNumbers() {
        return Arrays.asList(new Object[][]{
                {DummyCardEvaluator.class},
                {FixedScaleEvaluator.class}
        });
    }

    @Before
    public void setUp() throws Exception {
        inputCard = new MockCard();
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
            assertTrue(evaluation + " should not be less than " + valueForSecondCard, evaluation > valueForSecondCard);
        } else if (first.getWeight() < second.getWeight()) {
            assertTrue(evaluation + " should not be more than " + valueForSecondCard, evaluation < valueForSecondCard);
        } else fail("weights cannot be equal");
    }
}