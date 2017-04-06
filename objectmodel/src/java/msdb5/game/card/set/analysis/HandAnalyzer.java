package msdb5.game.card.set.analysis;

import msdb5.game.card.set.Hand;
import msdb5.game.card.Card;
import msdb5.game.card.CardSuit;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandAnalyzer {

    private final Hand handToEvaluate;

    public HandAnalyzer(Hand handToEvaluate) {
        this.handToEvaluate = handToEvaluate;
    }

    public HandAnalysisData analyze() {
        final int[] cardCountPerSuit = countCardsPerSuit(handToEvaluate.getCardSet().stream());
        final float averageCardsPerSuit = averageCountPerSuit(cardCountPerSuit);
        final boolean[] suitsWeakness = moreThanAverageCountPerSuit(cardCountPerSuit, averageCardsPerSuit);
        final int weaknessIndex = weaknessIndex(suitsWeakness);
        final Map<CardSuit, Integer> countMap = storeEvaluation(cardCountPerSuit);

        Arrays.sort(cardCountPerSuit);
        final int highestSuitIndex = 3;
        final int secondBestSuitIndex = 2;
        final int distanceFromSecond = cardCountPerSuit[highestSuitIndex] - cardCountPerSuit[secondBestSuitIndex];

        final HandAnalysisData suitAnalysis = new HandAnalysisData(countMap, averageCardsPerSuit, weaknessIndex, distanceFromSecond);
        return suitAnalysis;
    }


    private Map<CardSuit, Integer> storeEvaluation(int[] suitEvaluation) {
        final Map<CardSuit, Integer> suitEvaluationMap = new TreeMap<>();
        final int suitSize = CardSuit.values().length;

        for (int i = 0; i < suitSize; i++) {
            suitEvaluationMap.put(CardSuit.values()[i], suitEvaluation[i]);
        }

        return suitEvaluationMap;
    }

    private int[] countCardsPerSuit(Stream<Card> cardStream) {
        final int[] handSuitCount = new int[CardSuit.values().length];
        cardStream.forEach(card -> handSuitCount[card.getCardSuit().ordinal()]++);
        return handSuitCount;
    }

    private float averageCountPerSuit(int[] handSuitCount) {
        float total = 0F;
        int weight = 0;
        for (int count : handSuitCount) {
            if (count > 0) {
                total += count;
                weight++;
            }
        }
        return total / weight;
    }

    private boolean[] moreThanAverageCountPerSuit(int[] handSuitCount, float avgCardsPerSuit) {
        final int suitSize = CardSuit.values().length;
        boolean[] moreThanAverage = new boolean[suitSize];
        for (int i = 0; i < suitSize; i++) {
            moreThanAverage[i] = handSuitCount[i] >= avgCardsPerSuit;
        }
        return moreThanAverage;
    }

    private int weaknessIndex(boolean[] suitWeakness) {
        int weaknessIndex = 0;
        for (boolean weakness :
                suitWeakness) {
            if (weakness) weaknessIndex++;
        }
        return weaknessIndex;
    }

    @Override
    public String toString() {
        return "HandAnalyzer{" +
                "handToEvaluate=" + handToEvaluate +
                ", suitEvaluation=" + analyze() +
                '}';
    }
}
