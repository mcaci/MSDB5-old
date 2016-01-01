package game.mechanics.evaluation.card;

import game.elements.base.Card;

/**
 * Created by nikiforos on 10/09/15.
 */
public class DummyCardEvaluator implements ICardEvaluator {

    @Override
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
