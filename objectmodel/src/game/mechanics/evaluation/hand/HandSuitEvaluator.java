package game.mechanics.evaluation.hand;

import game.elements.base.CardSuit;
import game.elements.cardset.Hand;
import game.mechanics.analysis.HandSuitAnalyzer;

import java.util.Map;

/**
 * Created by nikiforos on 07/09/15.
 */
public class HandSuitEvaluator implements IHandEvaluator {

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        HandSuitAnalyzer handSuitAnalyzer = new HandSuitAnalyzer(handToEvaluate);
        Map<CardSuit, Float> analisysResult = handSuitAnalyzer.analyze();

//        TODO: incomplete

        return 60;
    }

}
