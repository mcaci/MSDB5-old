package msdb5.game.player.characteristic;

import msdb5.game.card.set.Hand;
import msdb5.game.card.set.HandFactoryTest;
import msdb5.game.player.*;
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

    private Player player;
    private Hand inputHand;
    private int handEvaluation;

    public HandEvaluatorTest(Supplier<Player> playerSupplier) {
        this.player = playerSupplier.get();
    }

    @Parameterized.Parameters
    public static Collection<?> initParams() {
        return Arrays.asList(new Supplier[][]{
                {MockClassicPlayer::new},
                {MockCowardPlayer::new},
                {MockUnwaveringPlayer::new}
        });
    }

    @Before
    public void setUp() throws Exception {
        inputHand = new HandFactoryTest(true).getMockHand();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Hand evaluated with " + this.player.getClass().getSimpleName() + ": " + this.inputHand + ", value: " + this.handEvaluation);
    }

    @Test
    public void testEvaluateHand() throws Exception {
        handEvaluation = evaluateHand(player::evaluateHand, inputHand);
        assertTrue(handEvaluation + " is more than 120", handEvaluation <= 120);
    }

    private int evaluateHand(IHandEvaluator handEvaluator, Hand inputHand) {
        return handEvaluator.applyAsInt(inputHand);
    }
}