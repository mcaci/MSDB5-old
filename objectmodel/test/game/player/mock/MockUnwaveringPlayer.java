package game.player.mock;

import game.cardset.Hand;
import game.cardset.card.Card;
import game.cardset.card.CardSuit;
import game.mechanics.analysis.card.FixedScaleAnalyzer;
import game.mechanics.analysis.card.ICardAnalyzer;
import game.mechanics.analysis.hand.HandAnalysisData;
import game.mechanics.analysis.hand.HandAnalyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockUnwaveringPlayer extends MockPlayer {

    public MockUnwaveringPlayer() {
        super(0F, 7);
    }

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

        return 60;
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

        ICardAnalyzer cardAnalyzer = new FixedScaleAnalyzer();

        for (Card card :
                handToEvaluate.getCardSet()) {
            CardSuit cardSuit = card.getCardSuit();
            Integer currentValue = sumSuitEvaluation.getOrDefault(cardSuit, 0);
            int evaluateCard = cardAnalyzer.analyze(card);
            sumSuitEvaluation.put(cardSuit, currentValue + evaluateCard);
        }
        return sumSuitEvaluation;
    }


}
