package msdb5.gameplay.endgame;

import msdb5.game.card.Card;
import msdb5.game.card.set.CardSet;
import msdb5.game.card.set.CollectedCardSetFactory;
import msdb5.gameplay.player.MockCard;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 18/02/16.
 */
public class CardSetScoreCounterTest {

    public static final CardSet<? extends Collection<Card>> CARDS = new CollectedCardSetFactory().create();
    private static int score;

    @BeforeClass
    public static void getScore() {
        IntStream.range(0, 20).forEach(i -> CARDS.add(new MockCard()));
        ForkJoinTask<Integer> taskToComputeScore = new CardSetScoreCounter(CARDS);
        ForkJoinPool pool = new ForkJoinPool();
        score = pool.invoke(taskToComputeScore);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("score count: displaying evaluated Hand");
        System.out.println(CARDS);
    }

    @Test
    public void testScoreComputedIsGreaterOrEqualsThanZero() throws Exception {
        assertTrue("score: " + score, score >= 0);
    }

    @Test
    public void testScoreComputedIsLessOrEqualsThan120() throws Exception {
        assertTrue("score: " + score, score <= 120);
    }
}