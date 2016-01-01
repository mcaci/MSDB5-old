package game.mechanics.evaluation.hand;

import game.cardset.Hand;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IHandEvaluator {

    int evaluateHand(Hand handToEvaluate);
}
