package game.elements.player.evaluation.hand;

import game.elements.base.Card;
import game.elements.cardset.Hand;
import game.elements.player.analysis.card.FixedScaleEvaluator;
import game.elements.player.analysis.card.ICardEvaluator;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by nikiforos on 10/09/15.
 */
public class SimpleWeightedSumEvaluator implements IHandEvaluator {

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        ICardEvaluator cardEvaluator = new FixedScaleEvaluator();
        Collection<Card> cards = handToEvaluate.getCardSet();
        int handValue = 0;
        Iterator<Card> cardIterator = cards.iterator();
        while (cardIterator.hasNext()) {
            int cardValue = cardEvaluator.evaluateCard(cardIterator.next());
            handValue += cardValue;
        }

        int weightedHandValue = (int) (handValue / 20.0) * 3;
        return weightedHandValue;
    }
}
