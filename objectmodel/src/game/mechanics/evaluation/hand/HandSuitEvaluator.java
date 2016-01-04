package game.mechanics.evaluation.hand;

import game.cardset.Hand;
import game.cardset.card.Card;
import game.cardset.card.CardSuit;
import game.mechanics.analysis.HandAnalyzer;
import game.mechanics.evaluation.card.FixedScaleEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikiforos on 07/09/15.
 */
public class HandSuitEvaluator implements IHandEvaluator {

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        HandAnalyzer handAnalyzer = new HandAnalyzer(handToEvaluate);
        Map<CardSuit, Integer> analisysResult = handAnalyzer.analyze().getSuitStrengthMap();

        // sum weights for each suit
        int[] sumOfWeights = new int[CardSuit.values().length];
        for (Card card :
                handToEvaluate.getCardSet()) {
            int weight = new FixedScaleEvaluator().evaluateCard(card);
            sumOfWeights[card.getCardSuit().ordinal()] += weight;
        }

        // store weights
        Map<CardSuit, Integer> weightAnalisys = new HashMap<>();
        for (int i = 0; i < sumOfWeights.length; i++) {
            CardSuit suit = CardSuit.values()[i];
            weightAnalisys.put(suit, analisysResult.get(suit) * sumOfWeights[i]);
        }

        // aggregate results
        float scoreThatCanBeMade = 0;
        for (Integer weight :
                weightAnalisys.values()) {
            scoreThatCanBeMade += weight;
        }
        scoreThatCanBeMade /= 4;

        return Math.round(scoreThatCanBeMade);
    }

}
