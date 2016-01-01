package game.mechanics.evaluation.card;

import game.elements.base.Card;
import game.elements.cardset.Deck;
import game.factory.cardset.DeckFactoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class AllCardsEvaluatorTestI extends BaseICardEvaluatorTest {

    // Declaring params and size
    private final static Deck FULL_DECK = new DeckFactoryTest().getMockDeck();
    private final static Class[] EVAL_IMPL_CLASSES = {DummyCardEvaluator.class, FixedScaleEvaluator.class};

    public AllCardsEvaluatorTestI(Class<?> implClass, Card inputCard) {
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


}