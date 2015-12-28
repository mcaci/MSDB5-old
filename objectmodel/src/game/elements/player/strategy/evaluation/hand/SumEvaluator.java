package game.elements.player.strategy.evaluation.hand;

import game.elements.base.Card;
import game.elements.cardset.DeckAwareHand;
import game.elements.player.strategy.evaluation.card.DummyCardEvaluator;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by nikiforos on 10/09/15.
 */
public class SumEvaluator implements IHandEvaluator {

    @Override
    public int evaluateHand(DeckAwareHand deckAwareHandToEvaluate) {
        DummyCardEvaluator cardEvaluator = new DummyCardEvaluator();
        Collection<Card> cards = deckAwareHandToEvaluate.getCardSet();
        int handValue = 0;
        Iterator<Card> cardIterator = cards.iterator();
        while (cardIterator.hasNext()) {
            int cardValue = cardEvaluator.evaluateCard(cardIterator.next());
            handValue += cardValue;
        }
        return handValue;
    }
}
