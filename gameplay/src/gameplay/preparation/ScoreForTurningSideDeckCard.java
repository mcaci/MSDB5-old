package gameplay.preparation;

/**
 * Created by nikiforos on 16/01/16.
 */
public enum ScoreForTurningSideDeckCard {
    NINETY(90), HUNDRED(100), HUNDRED_TEN(110), HUNDRED_TWENTY(120) {
        @Override
        public int numberOfCardToTurn() {
            return 2;
        }
    };

    private final int equivalentScore;

    ScoreForTurningSideDeckCard(int equivalentScore) {
        this.equivalentScore = equivalentScore;
    }

    public static ScoreForTurningSideDeckCard nextTurningAt(int referenceScore) {
        ScoreForTurningSideDeckCard nextTurningScore = HUNDRED_TWENTY;
        for (ScoreForTurningSideDeckCard score :
                values()) {
            if (referenceScore < score.getEquivalentInteger()) {
                nextTurningScore = score;
                break;
            }
        }
        return nextTurningScore;
    }

    public int numberOfCardToTurn() {
        return 1;
    }

    public int getEquivalentInteger() {
        return equivalentScore;
    }
}
