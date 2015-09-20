package strategy.evaluation.hand;

import game.elements.Card;
import game.elements.cardset.Hand;
import strategy.evaluation.card.DummyCardEvaluator;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by nikiforos on 10/09/15.
 */
public class SumEvaluator implements IHandEvaluator {

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        DummyCardEvaluator cardEvaluator = new DummyCardEvaluator();
        Collection<Card> cards = handToEvaluate.getCardSet();
        int handValue = 0;
        Iterator<Card> cardIterator = cards.iterator();
        while (cardIterator.hasNext()) {
            int cardValue = cardEvaluator.evaluateCard(cardIterator.next());
            handValue += cardValue;
        }
        return handValue;
    }
}
