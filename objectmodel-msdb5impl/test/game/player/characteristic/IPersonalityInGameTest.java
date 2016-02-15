package game.player.characteristic;

import game.cardset.Hand;
import game.factory.cardset.HandFactoryTest;
import org.junit.After;
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
public class IPersonalityInGameTest {

    private IPersonalityForPreparation iHandEvaluatorTestObject;

    private Class<?> implClass;
    private Hand inputHand;
    private int handEvaluation;

    public IPersonalityInGameTest(Class implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection<?> initParameters() {

        Object[][] params = new Object[CommonData.PERS_IMPL_CLASSES.length][];
        int i = 0;
        for (Class personalityClass : CommonData.PERS_IMPL_CLASSES) {
            params[i] = new Object[1];
            params[i][0] = personalityClass;
            i++;
        }
        return Arrays.asList(params);
    }

    @Before
    public void setUp() throws Exception {
        inputHand = new HandFactoryTest(true).getMockHand();
        Constructor<?> constructor = implClass.getConstructor();
        iHandEvaluatorTestObject = (IPersonalityForPreparation) constructor.newInstance();
        handEvaluation = iHandEvaluatorTestObject.evaluateHand(inputHand);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Hand evaluated with " + iHandEvaluatorTestObject.getClass().getSimpleName() + ": " + this.inputHand + ", value: " + handEvaluation);
    }

    @Test
    public void testEvaluateHand() throws Exception {
        assertTrue(handEvaluation + " is more than 120", handEvaluation <= 120);
    }
}