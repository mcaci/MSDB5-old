package msdb5.game.card.set.analysis;

import msdb5.game.card.CardSuit;
import msdb5.game.card.set.Hand;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandAnalyzer {

    private final Hand handToEvaluate;

    public HandAnalyzer(Hand handToEvaluate) {
        this.handToEvaluate = handToEvaluate;
    }

    public HandAnalysisData analyze() {
        final Map<CardSuit, Integer> countMap = this.handToEvaluate.getCardSet().stream().
                collect(Collectors.toMap(card -> card.getCardSuit(), card -> 1, Integer::sum));
        return new HandAnalysisData(countMap, this::averageCountPerSuit, this::getWeaknessIndex, this::getDistanceFromSecond);
    }

    private double averageCountPerSuit(Map<CardSuit, Integer> handSuitCount) {
        return handSuitCount.values().stream().mapToDouble(count -> count).average().orElseGet(() -> 0.0);
    }

    private int getWeaknessIndex(Map<CardSuit, Integer> cardCountPerSuit) {
        final Map<CardSuit, Boolean> suitsWeakness = moreThanAverageCountPerSuit(cardCountPerSuit);
        return suitsWeakness.values().stream().mapToInt((weakness) -> (weakness ? 1 : 0)).sum();
    }


    private Map<CardSuit, Boolean> moreThanAverageCountPerSuit(Map<CardSuit, Integer> handSuitCount) {
        double avgCardsPerSuit = handSuitCount.values().stream().mapToInt(count -> count).average().orElseGet(() -> 0.0);
        Map<CardSuit, Boolean> moreThanAverage = new HashMap<>();
        for (CardSuit suit : handSuitCount.keySet()) {
            moreThanAverage.put(suit, handSuitCount.get(suit) >= avgCardsPerSuit);
        }
        return moreThanAverage;
    }

    private int getDistanceFromSecond(Map<CardSuit, Integer> cardCountPerSuit) {
        int firstAndSecondSuitQuantity = 2;
        return cardCountPerSuit.values().stream().sorted().limit(firstAndSecondSuitQuantity).
                reduce((a, b) -> b - a).orElseGet(() -> 0);
    }

    @Override
    public String toString() {
        return "HandAnalyzer{" +
                "handToEvaluate=" + handToEvaluate +
                ", suitEvaluation=" + analyze() +
                '}';
    }
}
