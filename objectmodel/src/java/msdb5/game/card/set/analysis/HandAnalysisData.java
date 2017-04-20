package msdb5.game.card.set.analysis;

import msdb5.game.card.CardSuit;
import msdb5.game.card.set.Hand;

import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * Created by nikiforos on 04/01/16.
 */
public class HandAnalysisData {

    private final Map<CardSuit, Integer> suitStrengthMap;
    private final float suitDensity;
    private final int weaknessIndex;
    private final int distanceFromSecond;

    public HandAnalysisData(Hand hand, ToDoubleFunction<Hand> suitDensity, ToIntFunction<Hand> weaknessIndex, ToIntFunction<Hand> distanceFromSecond) {
        this.suitStrengthMap = hand.getCardSet().stream().
                collect(Collectors.toMap(card -> card.getCardSuit(), card -> 1, Integer::sum));
        this.suitDensity = (float) suitDensity.applyAsDouble(hand);
        this.weaknessIndex = weaknessIndex.applyAsInt(hand);
        this.distanceFromSecond = distanceFromSecond.applyAsInt(hand);
    }

    public Map<CardSuit, Integer> getSuitStrengthMap() {
        return suitStrengthMap;
    }

    public float getSuitDensity() {
        return suitDensity;
    }

    public int getWeaknessIndex() {
        return weaknessIndex;
    }

    public int getDistanceFromSecond() {
        return distanceFromSecond;
    }

    @Override
    public String toString() {
        return "HandAnalysisData{" +
                "suitStrengthMap=" + suitStrengthMap +
                ", suitDensity=" + suitDensity +
                ", weaknessIndex=" + weaknessIndex +
                ", distanceFromSecond=" + distanceFromSecond +
                '}';
    }
}