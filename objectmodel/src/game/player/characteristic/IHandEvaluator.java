package game.player.characteristic;

import game.cardset.Hand;

/**
 * Created by nikiforos on 10/01/16.
 */
public interface IHandEvaluator {

    int evaluateHand(Hand handToEvaluate);
}
