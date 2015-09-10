package strategy.evaluation.hand;

import game.player.Hand;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IHandEvaluator {

    int evaluateHand(Hand handToEvaluate);
}
