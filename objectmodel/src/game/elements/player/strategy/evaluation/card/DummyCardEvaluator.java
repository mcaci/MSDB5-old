package game.elements.player.strategy.evaluation.card;

import game.elements.base.Card;

/**
 * Created by nikiforos on 10/09/15.
 */
public class DummyCardEvaluator implements ICardEvaluator {

    @Override
    public int evaluateCard(Card card) {
        return 5;
    }

}
