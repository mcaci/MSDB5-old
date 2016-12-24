package com.msdb5.game.mechanics.analysis.hand;

import com.msdb5.game.cardset.Hand;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.cardset.card.CardSuit;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandAnalyzer {

    private final Hand handToEvaluate;

    public HandAnalyzer(Hand handToEvaluate) {
        this.handToEvaluate = handToEvaluate;
    }

    public HandAnalysisData analyze() {
        final int[] cardCountPerSuit = countCardsPerSuit();
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
//        final float handSize = handToEvaluate.size();
        final int suitSize = CardSuit.values().length;

        for (int i = 0; i < suitSize; i++) {
            suitEvaluationMap.put(CardSuit.values()[i], suitEvaluation[i]); // / handSize);
        }

        return suitEvaluationMap;
    }

    private int[] countCardsPerSuit() {
        final int suitSize = CardSuit.values().length;
        final int[] handSuitCount = new int[suitSize];
        for (Card card : handToEvaluate.getCardSet()) {
            handSuitCount[card.getCardSuit().ordinal()]++;
        }
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
