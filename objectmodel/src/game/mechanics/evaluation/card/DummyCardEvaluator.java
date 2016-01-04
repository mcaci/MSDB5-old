package game.mechanics.evaluation.card;

import game.cardset.card.Card;

/**
 * To be used in tests only - and not even since there is a good implementation
 * @TODO: decide if to convert to Mock or not
 * Created by nikiforos on 10/09/15.
 */
@Deprecated
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
