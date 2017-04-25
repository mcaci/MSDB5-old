package msdb5.gameplay.endgame;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;
import msdb5.game.card.set.CardSet;
import msdb5.game.card.set.HandFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 18/02/16.
 */
public class CardSetScoreCounterWithSpecificHandTest {

    private CardSet<? extends Collection<Card>> cardSet;
    private static int score;
    private Collection<Card> cards;

    @Before
    public void setUp() throws Exception {
        cardSet = HandFactory.createEmptyHand();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("score count: displaying evaluated Hand");
        System.out.println(cardSet);
        cards.clear();
    }

    @Test
    public void testScoreComputedWithHandScoring30() throws Exception {
        cards = cardSet.getCardSet();
        cards.add(new Card(CardNumber.ASSO, CardSuit.ORO));
        cards.add(new Card(CardNumber.CINQUE, CardSuit.ORO));
        cards.add(new Card(CardNumber.DONNA, CardSuit.ORO));
        cards.add(new Card(CardNumber.CAVALLO, CardSuit.SPADE));
        cards.add(new Card(CardNumber.RE, CardSuit.SPADE));
        cards.add(new Card(CardNumber.TRE, CardSuit.BASTONI));
        cards.add(new Card(CardNumber.DUE, CardSuit.BASTONI));
        ForkJoinTask<Integer> taskToComputeScore = new CardSetScoreCounter(cardSet);
        ForkJoinPool pool = new ForkJoinPool();
        score = pool.invoke(taskToComputeScore);
        assertTrue("score: " + score, score == 30);
    }

    @Test
    public void testScoreComputedWithSameHandScoring30ChangingSuits() throws Exception {
        cards = cardSet.getCardSet();
        cards.add(new Card(CardNumber.ASSO, CardSuit.COPPE));
        cards.add(new Card(CardNumber.CINQUE, CardSuit.BASTONI));
        cards.add(new Card(CardNumber.DONNA, CardSuit.SPADE));
        cards.add(new Card(CardNumber.CAVALLO, CardSuit.ORO));
        cards.add(new Card(CardNumber.RE, CardSuit.BASTONI));
        cards.add(new Card(CardNumber.TRE, CardSuit.ORO));
        cards.add(new Card(CardNumber.DUE, CardSuit.SPADE));
        ForkJoinTask<Integer> taskToComputeScore = new CardSetScoreCounter(cardSet);
        ForkJoinPool pool = new ForkJoinPool();
        score = pool.invoke(taskToComputeScore);
        assertTrue("score: " + score, score == 30);
    }

    @Test
    public void testScoreComputedWith4AcesAnd4Threes() throws Exception {
        cards = cardSet.getCardSet();
        cards.add(new Card(CardNumber.ASSO, CardSuit.ORO));
        cards.add(new Card(CardNumber.TRE, CardSuit.ORO));
        cards.add(new Card(CardNumber.ASSO, CardSuit.COPPE));
        cards.add(new Card(CardNumber.TRE, CardSuit.COPPE));
        cards.add(new Card(CardNumber.ASSO, CardSuit.SPADE));
        cards.add(new Card(CardNumber.TRE, CardSuit.SPADE));
        cards.add(new Card(CardNumber.ASSO, CardSuit.BASTONI));
        cards.add(new Card(CardNumber.TRE, CardSuit.BASTONI));
        ForkJoinTask<Integer> taskToComputeScore = new CardSetScoreCounter(cardSet);
        ForkJoinPool pool = new ForkJoinPool();
        score = pool.invoke(taskToComputeScore);
        assertTrue("score: " + score, score == 84);
    }

}