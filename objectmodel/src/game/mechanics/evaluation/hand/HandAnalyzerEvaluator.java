package game.mechanics.evaluation.hand;

import game.cardset.Hand;
import game.cardset.card.Card;
import game.cardset.card.CardSuit;
import game.mechanics.analysis.HandAnalysisData;
import game.mechanics.analysis.HandAnalyzer;
import game.mechanics.evaluation.card.FixedScaleEvaluator;
import game.mechanics.evaluation.card.ICardEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikiforos on 07/01/16.
 */
public class HandAnalyzerEvaluator implements IHandEvaluator {

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        HandAnalyzer analyzer = new HandAnalyzer(handToEvaluate);
        HandAnalysisData analysisData = analyzer.analyze();

        Map<CardSuit, Integer> evaluationPerSuit = suitEvaluated(handToEvaluate);

        int evaluatedMaxScore = internalLogicThatUsesAnalysisDataAndHandEvaluationMap(analysisData, evaluationPerSuit);

        return evaluatedMaxScore;
    }

    private int internalLogicThatUsesAnalysisDataAndHandEvaluationMap(HandAnalysisData analysisData, Map<CardSuit, Integer> evaluationPerSuit) {
        // TODO: incomplete

        System.out.println(analysisData);
        System.out.println(evaluationPerSuit);

        return 0;
    }

    private Map<CardSuit, Integer> suitEvaluated(Hand handToEvaluate) {
        Map<CardSuit, Integer> sumSuitEvaluation = getCardSuitIntegerMap(handToEvaluate);
        weightValuesInMap(sumSuitEvaluation);
        return sumSuitEvaluation;
    }

    private void weightValuesInMap(Map<CardSuit, Integer> sumSuitEvaluation) {
        for (Map.Entry<CardSuit, Integer> suitValue :
                sumSuitEvaluation.entrySet()) {
            CardSuit cardSuit = suitValue.getKey();
            int currentValue = suitValue.getValue();
            int weightedValue = (int) (currentValue / 20.0) * 3;
            sumSuitEvaluation.put(cardSuit, weightedValue);
        }
    }

    private Map<CardSuit, Integer> getCardSuitIntegerMap(Hand handToEvaluate) {
        Map<CardSuit, Integer> sumSuitEvaluation = new HashMap<>();

        ICardEvaluator cardEvaluator = new FixedScaleEvaluator();
        for (Card card :
                handToEvaluate.getCardSet()) {
            CardSuit cardSuit = card.getCardSuit();
            Integer currentValue = sumSuitEvaluation.getOrDefault(cardSuit, 0);
            int evaluateCard = cardEvaluator.evaluateCard(card);
            sumSuitEvaluation.put(cardSuit, currentValue + evaluateCard);
        }
        return sumSuitEvaluation;
    }
}
