package game.elements.player.strategy.evaluation.hand;

import game.elements.cardset.DeckAwareHand;
import game.elements.cardset.MockDeckAwareHand;
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
public class IDeckAwareHandEvaluatorTest {

    private IHandEvaluator iHandEvaluatorTestObject;

    private Class<?> implClass;
    private DeckAwareHand inputDeckAwareHand;

    public IDeckAwareHandEvaluatorTest(Class implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection<?> primeNumbers() {
        return Arrays.asList(new Object[][]{
                {DummyHandEvaluator.class}
        });
    }

    @Before
    public void setUp() throws Exception {
        inputDeckAwareHand = new MockDeckAwareHand(true);
        Constructor<?> constructor = implClass.getConstructor();
        iHandEvaluatorTestObject = (IHandEvaluator) constructor.newInstance();
    }

    @Test
    public void testEvaluateHand() throws Exception {
        int evaluation = iHandEvaluatorTestObject.evaluateHand(inputDeckAwareHand);

        assertTrue(evaluation + " is less than 60", evaluation >= 60);
        assertTrue(evaluation + " is more than 120", evaluation <= 120);
    }
}