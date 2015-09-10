package strategy.evaluation.card;

import game.elements.Card;
import game.elements.MockCard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class ICardEvaluatorTest {

    private ICardEvaluator iCardEvaluatorTestObject;

    private Class implClass;
    private Card inputCard;

    public ICardEvaluatorTest(Class implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {DummyCardEvaluator.class}
//                ,
//                {SumEvaluator.class}
        });
    }

    @Before
    public void setUp() throws Exception {
        inputCard = new MockCard();
        Constructor<?> constructor = implClass.getConstructor();
        iCardEvaluatorTestObject = (ICardEvaluator) constructor.newInstance();
    }

    @Test
    public void testEvaluateCard() throws Exception {
        int evaluation = iCardEvaluatorTestObject.evaluateCard(inputCard);

        assertTrue(evaluation + " is less than 60", evaluation >= 0);
        assertTrue(evaluation + " is more than 120", evaluation <= 10);
    }
}