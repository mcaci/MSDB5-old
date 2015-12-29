package game.elements.player.evaluation.hand;

import game.elements.cardset.Hand;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IHandEvaluator {

    int evaluateHand(Hand handToEvaluate);
}
