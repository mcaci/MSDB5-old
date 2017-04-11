package msdb5.game.card.set.analysis;

import msdb5.game.card.CardSuit;
import msdb5.game.card.set.Hand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandAnalyzer {

    private final Hand handToEvaluate;

    public HandAnalyzer(Hand handToEvaluate) {
        this.handToEvaluate = handToEvaluate;
    }

    public HandAnalysisData analyze() {
        final Map<CardSuit, Integer> countMap = this.handToEvaluate.getCardSet().stream().collect(
                HashMap::new,
                (map, card) -> map.merge(card.getCardSuit(), 1, (a, b) -> a + b),
                HashMap::putAll);
        return new HandAnalysisData(countMap, this::averageCountPerSuit, this::getWeaknessIndex, this::getDistanceFromSecond);
    }

    private float averageCountPerSuit(Map<CardSuit, Integer> handSuitCount) {
        return (float) handSuitCount.values().stream().mapToDouble(count -> count).average().orElseGet(() -> 0.0);
    }

    private int getWeaknessIndex(Map<CardSuit, Integer> cardCountPerSuit) {
        final Map<CardSuit, Boolean> suitsWeakness = moreThanAverageCountPerSuit(cardCountPerSuit);
        return suitsWeakness.values().stream().mapToInt((weakness) -> (weakness ? 1 : 0)).sum();
    }


    private Map<CardSuit, Boolean> moreThanAverageCountPerSuit(Map<CardSuit, Integer> handSuitCount) {
        float avgCardsPerSuit = (float) handSuitCount.values().stream().mapToDouble(count -> count).average().orElseGet(() -> 0.0);
        Map<CardSuit, Boolean> moreThanAverage = new HashMap<>();
        for (CardSuit suit : handSuitCount.keySet()) {
            Integer count = handSuitCount.get(suit);
            moreThanAverage.put(suit, count >= avgCardsPerSuit);
        }
        return moreThanAverage;
    }

    private int getDistanceFromSecond(Map<CardSuit, Integer> cardCountPerSuit) {
        return cardCountPerSuit.values().stream().sorted().limit(2).reduce((a, b) -> b - a).orElseGet(() -> 0);
    }

    @Override
    public String toString() {
        return "HandAnalyzer{" +
                "handToEvaluate=" + handToEvaluate +
                ", suitEvaluation=" + analyze() +
                '}';
    }
}
