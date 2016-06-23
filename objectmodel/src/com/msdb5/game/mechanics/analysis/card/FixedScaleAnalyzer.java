package com.msdb5.game.mechanics.analysis.card;

import com.msdb5.game.cardset.card.Card;

/**
 * Created by nikiforos on 29/12/15.
 */
public class FixedScaleAnalyzer implements ICardAnalyzer {

    @Override
    public int analyze(Card card) {
        final int weight = card.getCardNumber().getWeight();
        int points = card.pointsForTheCard();
        if (points == 0) {
            points++;
        }
        return points * weight;
    }
}
