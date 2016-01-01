package game.mechanics.evaluation.hand;

import game.elements.cardset.Hand;

/**
 * Created by nikiforos on 07/09/15.
 */
public class DummyHandEvaluator implements IHandEvaluator {

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        return 60;
    }

}
