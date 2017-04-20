package msdb5.game.card.set.analysis;

import msdb5.game.card.Card;
import msdb5.game.card.CardSuit;
import msdb5.game.card.set.Hand;

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
        return new HandAnalysisData(handToEvaluate,
                this::computeSuitDensity,
                this::getWeaknessIndex,
                this::getDistanceFromSecond);
    }

    private double computeSuitDensity(Hand hand) {
        return hand.size() / (float) CardSuit.values().length;
    }

    private int getWeaknessIndex(Hand hand) {
        return 1;
    }

    private int getDistanceFromSecond(Hand hand) {
        return hand.getCardSet().stream().
                collect(Collectors.groupingBy(Card::getCardSuit, Collectors.counting()))
                .values().stream().sorted().limit(2).
                        reduce((second, first) -> first - second).orElse(0L).intValue();
    }

    @Override
    public String toString() {
        return "HandAnalyzer{" +
                "handToEvaluate=" + handToEvaluate +
                ", suitEvaluation=" + analyze() +
                '}';
    }
}
