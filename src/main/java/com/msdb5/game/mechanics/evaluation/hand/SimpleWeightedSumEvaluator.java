package com.msdb5.game.mechanics.evaluation.hand;


import java.util.Collection;
import java.util.Iterator;

import com.msdb5.game.cardset.Hand;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.mechanics.evaluation.card.FixedScaleEvaluator;
import com.msdb5.game.mechanics.evaluation.card.ICardEvaluator;

/**
 * Created by nikiforos on 10/09/15.
 */
public class SimpleWeightedSumEvaluator implements IHandEvaluator {

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
