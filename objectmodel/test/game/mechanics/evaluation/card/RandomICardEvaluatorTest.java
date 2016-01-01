package game.mechanics.evaluation.card;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class RandomICardEvaluatorTest extends BaseICardEvaluatorTest {

    public RandomICardEvaluatorTest(Class<?> implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection<?> parameters() {
        return Arrays.asList(new Object[][]{
                {DummyCardEvaluator.class},
                {FixedScaleEvaluator.class}
        });
    }

}