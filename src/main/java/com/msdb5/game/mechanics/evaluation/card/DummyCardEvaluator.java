package com.msdb5.game.mechanics.evaluation.card;

import com.msdb5.game.cardset.card.Card;

/**
 * Created by nikiforos on 10/09/15.
 */
public class DummyCardEvaluator implements ICardEvaluator {

    public int evaluateCard(Card card) {
        int value = card.getCardNumber().pointsForTheCard();
        if (value > 0) {
            value += 10;
        } else {
            value = card.getCardNumber().ordinal();
        }
        return value;
    }

}