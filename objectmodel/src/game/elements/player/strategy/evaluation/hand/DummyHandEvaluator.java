package game.elements.player.strategy.evaluation.hand;

import game.elements.cardset.DeckAwareHand;

/**
 * Created by nikiforos on 07/09/15.
 */
public class DummyHandEvaluator implements IHandEvaluator {

    @Override
    public int evaluateHand(DeckAwareHand deckAwareHandToEvaluate) {
        return 60;
    }

}
