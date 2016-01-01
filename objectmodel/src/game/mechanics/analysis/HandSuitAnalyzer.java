package game.mechanics.analysis;

import game.elements.base.Card;
import game.elements.base.CardSuit;
import game.elements.cardset.Hand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandSuitAnalyzer {

    private final Hand handToEvaluate;

    public HandSuitAnalyzer(Hand handToEvaluate) {
        this.handToEvaluate = handToEvaluate;
    }

    public Map<CardSuit, Float> analyze() {
        final int[] suitEvaluation = countCardsPerSuit();
        final Map<CardSuit, Float> suitEvaluationMap = storeEvaluation(suitEvaluation);
        return suitEvaluationMap;
    }

    private Map<CardSuit, Float> storeEvaluation(int[] suitEvaluation) {
        final Map<CardSuit, Float> suitEvaluationMap = new HashMap<>();
        final float handSize = handToEvaluate.size();
        final int suitSize = CardSuit.values().length;

        for (int i = 0; i < suitSize; i++) {
            suitEvaluationMap.put(CardSuit.values()[i], suitEvaluation[i] / handSize);
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

    @Override
    public String toString() {
        return "HandSuitAnalyzer{" +
                "handToEvaluate=" + handToEvaluate +
                ", suitEvaluation=" + Arrays.toString(analyze().entrySet().toArray()) +
                '}';
    }
}