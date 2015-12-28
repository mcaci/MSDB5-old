package game.elements.player.strategy.evaluation.hand;

import game.elements.cardset.DeckAwareHand;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IHandEvaluator {

    int evaluateHand(DeckAwareHand deckAwareHandToEvaluate);
}
