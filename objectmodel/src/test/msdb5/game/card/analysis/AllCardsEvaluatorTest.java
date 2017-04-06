package msdb5.game.card.analysis;

import msdb5.game.card.set.Deck;
import msdb5.game.card.Card;
import msdb5.game.card.set.DeckFactoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class AllCardsEvaluatorTest extends BaseCardEvaluatorTest {

    // Declaring params and size
    private final static Deck FULL_DECK = new DeckFactoryTest().getMockDeck();

    public AllCardsEvaluatorTest(Class<?> implClass, Card inputCard) {
        this.implClass = implClass;
        this.inputCard = inputCard;
    }

    @Parameterized.Parameters
    public static Collection<?> parameters() {
        Object[][] params = new Object[EVAL_IMPL_CLASSES.length * Deck.DEFAULT_DECK_SIZE][];
        Iterator<Card> cardsIterator = FULL_DECK.getCardSet().iterator();
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[2];
            params[i][0] = EVAL_IMPL_CLASSES[i / Deck.DEFAULT_DECK_SIZE];
            Card nextCard = cardsIterator.next();
            params[i][1] = nextCard;
            if (!cardsIterator.hasNext()) {
                cardsIterator = FULL_DECK.getCardSet().iterator();
            }
        }
        return Arrays.asList(params);
    }


}