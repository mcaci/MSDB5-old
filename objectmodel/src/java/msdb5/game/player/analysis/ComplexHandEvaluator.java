package msdb5.game.player.analysis;

import msdb5.game.card.Card;
import msdb5.game.card.CardSuit;
import msdb5.game.card.analysis.FixedScaleAnalyzer;
import msdb5.game.card.set.Hand;
import msdb5.game.card.set.analysis.HandAnalysisData;
import msdb5.game.card.set.analysis.HandAnalyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created by mcaci on 4/7/17.
 */
public class ComplexHandEvaluator implements IHandEvaluator {

    private static final double WEIGHT = 3.0/20.0;

    @Override
    public int evaluate(Hand hand) {
        int value = 60;
        HandAnalyzer analyzer = new HandAnalyzer(hand);
        HandAnalysisData analysisData = analyzer.analyze();
        int suitDensityAdder = Math.round(2 * analysisData.getSuitDensity());
        int weaknessIndexAdder = 12 / analysisData.getWeaknessIndex();
        int distanceFromSecondAdder = 3 * analysisData.getDistanceFromSecond();
        int suitHighestValue = getSuitHighestValue(hand);
        int total = value + suitDensityAdder + weaknessIndexAdder + distanceFromSecondAdder + suitHighestValue;

        return Math.round(0.5F * total);
    }

    private int getSuitHighestValue(Hand hand) {
        BiConsumer<Map<CardSuit, Integer>, Card> cardSuitMapper = collectSummedCardValuesInMap();
        Stream<Card> cardStream = hand.getCardSet().stream();
        Optional<Integer> max = cardStream.collect(HashMap::new, cardSuitMapper, Map::putAll).values().stream().max(Integer::compareTo);
        return (max.isPresent() ? max.get() : 0);
    }

    private BiConsumer<Map<CardSuit, Integer>, Card> collectSummedCardValuesInMap() {
        return (Map<CardSuit, Integer> map, Card card) -> {
            BinaryOperator<Integer> sum = (currentValue, cardEvaluation) -> (int) ((currentValue + cardEvaluation) * WEIGHT);
            map.merge(card.getCardSuit(), new FixedScaleAnalyzer().analyze(card), sum);
        };
    }
}
