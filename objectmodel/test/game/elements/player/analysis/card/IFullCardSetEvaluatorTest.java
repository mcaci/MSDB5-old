package game.elements.player.analysis.card;

import game.elements.base.Card;
import game.elements.base.CardNumber;
import game.elements.base.MockCard;
import game.elements.cardset.Deck;
import game.elements.cardset.MockDeck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class IFullCardSetEvaluatorTest {

    // Declaring params and size
    private final static Deck FULL_DECK = new MockDeck();
    private final static Class[] EVAL_IMPL_CLASSES = {DummyCardEvaluator.class, FixedScaleEvaluator.class};
    private ICardEvaluator iCardEvaluatorTestObject;
    private Class<?> implClass;
    private Card inputCard;
    private int evaluation = 0;

    public IFullCardSetEvaluatorTest(Class<?> implClass, Card inputCard) {
        this.implClass = implClass;
        this.inputCard = inputCard;
    }

    @Parameterized.Parameters
    public static Collection<?> parameters() {
        Object[][] params = new Object[EVAL_IMPL_CLASSES.length * Deck.DECK_DEFAULT_SIZE][];
        Iterator<Card> cardsIterator = FULL_DECK.getCardSet().iterator();
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[2];
            int implIndex = (i < 40 ? 0 : 1);
            params[i][0] = EVAL_IMPL_CLASSES[implIndex];
            Card nextCard = cardsIterator.next();
            params[i][1] = nextCard;
            if (!cardsIterator.hasNext()) {
                cardsIterator = FULL_DECK.getCardSet().iterator();
            }
        }
        return Arrays.asList(params);
    }

    @Before
    public void setUp() throws Exception {
        Constructor<?> constructor = implClass.getConstructor();
        iCardEvaluatorTestObject = (ICardEvaluator) constructor.newInstance();
        evaluation = iCardEvaluatorTestObject.evaluateCard(inputCard);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Card Evaluated with " + iCardEvaluatorTestObject.getClass().getSimpleName() + ": " + this.inputCard + ", value: " + evaluation);
    }

    @Test
    public void testValidity() throws Exception {
        assertTrue(evaluation + " is less than 0", evaluation >= 0);
    }

    @Test
    public void testConsistency() throws Exception {
        int secondEvaluation = iCardEvaluatorTestObject.evaluateCard(inputCard);
        assertEquals("Second evaluation <" + secondEvaluation +
                        "> is not the same as the first one <" + evaluation + ">",
                evaluation, secondEvaluation);
    }

    @Test
    public void testWeightConsistency() throws Exception {
        Card secondCard = new MockCard();
        CardNumber first = inputCard.getCardNumber();
        CardNumber second = secondCard.getCardNumber();
//        System.out.println("Second card is: " + secondCard); // TODO: to replace with logs
        while (first.equals(second)) {

//            System.out.println("Second card is: " + secondCard); // TODO: to replace with logs
            secondCard = new MockCard();
            second = secondCard.getCardNumber();
        }
        int valueForSecondCard = iCardEvaluatorTestObject.evaluateCard(secondCard);
        if (first.getWeight() > second.getWeight()) {
            assertTrue("Evaluation for card " + inputCard + "that is " +
                            evaluation + " should not be less than " +
                            "Evaluation for card " + secondCard + "that is " +
                            valueForSecondCard,
                    evaluation > valueForSecondCard);
        } else if (first.getWeight() < second.getWeight()) {
            assertTrue("Evaluation for card " + inputCard + "that is " +
                            evaluation + " should not be more than " +
                            "Evaluation for card " + secondCard + "that is " +
                            valueForSecondCard,
                    evaluation < valueForSecondCard);
        } else fail("weights cannot be equal");
    }
}