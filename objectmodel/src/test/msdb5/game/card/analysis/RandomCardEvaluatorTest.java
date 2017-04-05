package msdb5.game.card.analysis;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class RandomCardEvaluatorTest extends BaseCardEvaluatorTest {

    public RandomCardEvaluatorTest(Class<?> implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection<?> parameters() {
        Object[][] params = new Object[EVAL_IMPL_CLASSES.length][];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[1];
            params[i][0] = EVAL_IMPL_CLASSES[i];
        }
        return Arrays.asList(params);
    }

}