package msdb5.gameplay.endgame;

import msdb5.game.card.set.Hand;
import msdb5.game.card.set.HandFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 18/02/16.
 */
public class CardSetScoreCounterTest {

    public static final Hand CARDS = new HandFactory(false).create();
    private static int score;

    @BeforeClass
    public static void getScore() {
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