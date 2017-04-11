package msdb5.game.card.set.analysis;

import msdb5.game.card.CardSuit;

import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

/**
 * Created by nikiforos on 04/01/16.
 */
public class HandAnalysisData {

    private final Map<CardSuit, Integer> suitStrengthMap;
    private final float suitDensity;
    private final int weaknessIndex;
    private final int distanceFromSecond;

    public HandAnalysisData(Map<CardSuit, Integer> suitStrengthMap, float suitDensity, int weaknessIndex, int distanceFromSecond) {
        this.suitStrengthMap = suitStrengthMap;
        this.suitDensity = suitDensity;
        this.weaknessIndex = weaknessIndex;
        this.distanceFromSecond = distanceFromSecond;
    }

    public HandAnalysisData(Map<CardSuit, Integer> suitStrengthMap, ToDoubleFunction<Map<CardSuit, Integer>> suitDensity, ToIntFunction<Map<CardSuit, Integer>> weaknessIndex, ToIntFunction<Map<CardSuit, Integer>> distanceFromSecond) {
        this.suitStrengthMap = suitStrengthMap;
        this.suitDensity = (float) suitDensity.applyAsDouble(suitStrengthMap);
        this.weaknessIndex = weaknessIndex.applyAsInt(suitStrengthMap);
        this.distanceFromSecond = distanceFromSecond.applyAsInt(suitStrengthMap);
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