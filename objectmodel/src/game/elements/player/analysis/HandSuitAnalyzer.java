package game.elements.player.analysis;

import game.elements.base.Card;
import game.elements.base.CardSuit;
import game.elements.cardset.Hand;

import java.util.Arrays;

/**
 * Created by nikiforos on 29/12/15.
 */
public class HandSuitAnalyzer {

    private final Hand evaluatedHand;

    private final float[] suitEvaluation = new float[CardSuit.values().length];

    public HandSuitAnalyzer(Hand evaluatedHand) {
        this.evaluatedHand = evaluatedHand;
        for (int i = 0; i < suitEvaluation.length; i++) {
            suitEvaluation[i] = 0;
        }
    }

    public void evaluate() {
        final int handSize = evaluatedHand.size();
        for (Card card : evaluatedHand.getCardSet()) {
            suitEvaluation[card.getCardSuit().ordinal()]++;
        }
        for (int i = 0; i < suitEvaluation.length; i++) {
            suitEvaluation[i] /= handSize;
        }
    }

    public float[] getSuitEvaluation() {
        return suitEvaluation;
    }

    @Override
    public String toString() {
        return "HandSuitAnalyzer{" +
                "evaluatedHand=" + evaluatedHand +
                ", suitEvaluation=" + Arrays.toString(suitEvaluation) +
                '}';
    }
}
