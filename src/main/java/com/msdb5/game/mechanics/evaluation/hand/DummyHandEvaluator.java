package com.msdb5.game.mechanics.evaluation.hand;

import com.msdb5.game.cardset.Hand;

/**
 * Created by nikiforos on 07/09/15.
 */
public class DummyHandEvaluator implements IHandEvaluator {

    public int evaluateHand(Hand handToEvaluate) {
        return 60;
    }
}