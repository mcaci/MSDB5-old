package msdb5.game.player.analysis;

import msdb5.game.card.set.Hand;
import msdb5.game.card.set.HandFactoryTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class HandEvaluatorTest {

    private IHandEvaluator handEvaluator;
    private Hand inputHand;
    private int handEvaluation;

    public HandEvaluatorTest(Supplier<IHandEvaluator> handEvaluatorSupplier) {
        this.handEvaluator = handEvaluatorSupplier.get();
    }

    @Parameterized.Parameters
    public static Collection<?> initParams() {
        return Arrays.asList(new Supplier[][]{
                {RialzatoreHandEvaluator::new},
                {SimpleHandEvaluator::new},
                {ComplexHandEvaluator::new}
        });
    }

    @Before
    public void setUp() throws Exception {
        inputHand = new HandFactoryTest(true).getMockHand();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Hand evaluated with " + this.handEvaluator.getClass().getSimpleName() + ": " + this.inputHand + ", value: " + this.handEvaluation);
    }

    @Test
    public void testEvaluateHand() throws Exception {
        this.handEvaluation = this.handEvaluator.evaluate(this.inputHand);
        assertTrue(this.handEvaluation + " is more than 120", this.handEvaluation <= 120);
    }
}