package msdb5.game.player;

import msdb5.game.card.set.Hand;
import msdb5.game.card.Card;
import msdb5.game.card.CardSuit;
import msdb5.game.card.analysis.FixedScaleAnalyzer;
import msdb5.game.card.set.analysis.HandAnalysisData;
import msdb5.game.card.set.analysis.HandAnalyzer;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockUnwaveringPlayer extends MockPlayer {

    public MockUnwaveringPlayer() {
        super(0F, 5);
    }

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        return mapHandToCards().applyAsInt(handToEvaluate);
    }

    private ToIntFunction<Hand> mapHandToCards() {
        return (Hand hand) -> {
            int value = 60;
            HandAnalyzer analyzer = new HandAnalyzer(hand);
            HandAnalysisData analysisData = analyzer.analyze();
            int suitDensityAdder = Math.round(2 * analysisData.getSuitDensity());
            int weaknessIndexAdder = 12 / analysisData.getWeaknessIndex();
            int distanceFromSecondAdder = 3 * analysisData.getDistanceFromSecond();
            int suitHighestValue = getSuitHighestValue(hand);
            int total = value + suitDensityAdder + weaknessIndexAdder + distanceFromSecondAdder + suitHighestValue;

            return Math.round(0.85F * total);
        };
    }

    private int getSuitHighestValue(Hand hand) {
        BiConsumer<Map<CardSuit, Integer>, Card> cardSuitMapper = getMapCardBiConsumer();
        Stream<Card> cardStream = hand.getCardSet().stream();
        Optional<Integer> max = cardStream.collect(HashMap::new, cardSuitMapper, Map::putAll).values().stream().max(Integer::compareTo);
        return (max.isPresent() ? max.get() : 0);
    }

    private BiConsumer<Map<CardSuit, Integer>, Card> getMapCardBiConsumer() {
        return (Map<CardSuit, Integer> map, Card card) -> {
            CardSuit cardSuit = card.getCardSuit();
            Integer currentValue = map.getOrDefault(cardSuit, 0);
            int cardEvaluation = new FixedScaleAnalyzer().analyze(card);
            int finalValue = (int) ((currentValue + cardEvaluation) / 20.0) * 3;
            map.put(cardSuit, finalValue);
        };
    }
}
