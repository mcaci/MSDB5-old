package com.msdb5.game.mechanics.evaluation.card;

import com.msdb5.game.cardset.card.Card;

/**
 * Created by nikiforos on 29/12/15.
 */
public class FixedScaleEvaluator implements ICardEvaluator {
	
    public int evaluateCard(Card card) {
        int points = card.getCardNumber().pointsForTheCard();
        if (points == 0) {
            points++;
        }
        return points * card.getCardNumber().getWeight();
    }
}