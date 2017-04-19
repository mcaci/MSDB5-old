package msdb5.game.player.analysis;

import msdb5.game.card.Card;
import msdb5.game.card.analysis.FixedScaleAnalyzer;
import msdb5.game.card.set.Hand;
import msdb5.game.card.set.analysis.HandAnalysisData;
import msdb5.game.card.set.analysis.HandAnalyzer;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mcaci on 4/7/17.
 */
public class ComplexHandEvaluator implements IHandEvaluator {

    private static final double WEIGHT = 3.0/20.0;

    @Override
    public int evaluate(Hand hand) {
        int value = 60;
        FixedScaleAnalyzer cardAnalyzer = new FixedScaleAnalyzer();
        HandAnalysisData analysisData = new HandAnalyzer(hand).analyze();
        int suitDensityAdder = Math.round(2 * analysisData.getSuitDensity());
        int weaknessIndexAdder = 12 / analysisData.getWeaknessIndex();
        int distanceFromSecondAdder = 3 * analysisData.getDistanceFromSecond();
        int suitHighestValue = getSuitHighestValue(hand.getCardSet().stream(), cardAnalyzer::analyze);
        int total = value + suitDensityAdder + weaknessIndexAdder + distanceFromSecondAdder + suitHighestValue;

        return Math.round(0.5F * total);
    }

    private int getSuitHighestValue(Stream<Card> cardStream, Function<Card, Integer> cardAnalyzerFunction) {
        BinaryOperator<Integer> weightedSumOperator = (currentValue, cardEvaluation) -> (int) ((currentValue + cardEvaluation) * WEIGHT);
        return cardStream.
                collect(Collectors.toMap(Card::getCardSuit, cardAnalyzerFunction, weightedSumOperator)).
                values().
                stream().
                max(Integer::compareTo).
                orElseGet(() -> 0);
    }
}
