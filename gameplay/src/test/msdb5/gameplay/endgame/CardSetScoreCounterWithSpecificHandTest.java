package msdb5.gameplay.endgame;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;
import msdb5.game.card.set.CardSet;
import msdb5.game.card.set.CollectedCardSetFactory;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 18/02/16.
 */
public class CardSetScoreCounterWithSpecificHandTest {

    public static final CardSet<? extends Collection<Card>> CARDS = new CollectedCardSetFactory().create();
    private static int score;

    @BeforeClass
    public static void getScore() {
        CARDS.add(new Card(CardNumber.ASSO, CardSuit.ORO));
        CARDS.add(new Card(CardNumber.CINQUE, CardSuit.ORO));
        CARDS.add(new Card(CardNumber.DONNA, CardSuit.ORO));
        CARDS.add(new Card(CardNumber.CAVALLO, CardSuit.SPADE));
        CARDS.add(new Card(CardNumber.RE, CardSuit.SPADE));
        CARDS.add(new Card(CardNumber.TRE, CardSuit.BASTONI));
        CARDS.add(new Card(CardNumber.DUE, CardSuit.BASTONI));
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
    public void testScoreComputed() throws Exception {
        assertTrue("score: " + score, score == 30);
    }
}