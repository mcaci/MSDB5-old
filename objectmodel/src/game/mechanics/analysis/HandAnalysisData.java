package game.mechanics.analysis;

import game.cardset.card.CardSuit;

import java.util.Map;

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