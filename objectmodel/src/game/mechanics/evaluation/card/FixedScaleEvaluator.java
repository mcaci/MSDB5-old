package game.mechanics.evaluation.card;

import game.cardset.card.Card;

/**
 * Created by nikiforos on 29/12/15.
 */
public class FixedScaleEvaluator implements ICardEvaluator {
    @Override
    public int evaluateCard(Card card) {
        int points = card.getCardNumber().pointsForTheCard();
        if (points == 0) {
            points++;
        }
        return points * card.getCardNumber().getWeight();
    }
}
