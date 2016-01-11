package com.msdb5.game.mechanics.evaluation.hand;

import com.msdb5.game.cardset.Hand;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IHandEvaluator {

    int evaluateHand(Hand handToEvaluate);
}