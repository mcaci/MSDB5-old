package msdb5.game.card.set.analysis;

import msdb5.game.card.Card;
import msdb5.game.card.CardSuit;
import msdb5.game.card.set.Hand;

import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.function.Predicate;
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
                collect(Collectors.groupingBy(Card::getCardSuit, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
        return new HandAnalysisData(countMap, this::averageCountPerSuit, this::getWeaknessIndex, this::getDistanceFromSecond);
    }

    private double averageCountPerSuit(Map<CardSuit, Integer> handSuitCount) {
        return handSuitCount.values().stream().mapToDouble(Integer::doubleValue).average().orElseGet(() -> 0.0);
    }

    private int getWeaknessIndex(Map<CardSuit, Integer> cardCountPerSuit) {
        double avgCardsPerSuit = cardCountPerSuit.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
        Predicate<Integer> valuePredicate = i -> i >= avgCardsPerSuit;
        return (int) cardCountPerSuit.values().stream().filter(valuePredicate).count();
    }

    private int getDistanceFromSecond(Map<CardSuit, Integer> cardCountPerSuit) {
        int firstAndSecondSuitQuantity = 2;
        IntSummaryStatistics countStatistics = cardCountPerSuit.values().stream().
                sorted().
                limit(firstAndSecondSuitQuantity).
                mapToInt(Integer::intValue).
                summaryStatistics();
        return countStatistics.getMax() - countStatistics.getMin();
    }

    @Override
    public String toString() {
        return "HandAnalyzer{" +
                "handToEvaluate=" + handToEvaluate +
                ", suitEvaluation=" + analyze() +
                '}';
    }
}
