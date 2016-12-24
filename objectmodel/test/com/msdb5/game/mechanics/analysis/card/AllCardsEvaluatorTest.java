package com.msdb5.game.mechanics.analysis.card;

import com.msdb5.game.cardset.Deck;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.factory.cardset.DeckFactoryTest;
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