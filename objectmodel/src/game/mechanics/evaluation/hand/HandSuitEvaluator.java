package game.mechanics.evaluation.hand;

import game.cardset.Hand;
import game.cardset.card.CardSuit;
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

//      TODO: incomplete



        return 60;
    }

}
