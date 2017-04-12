package msdb5.game.player;

import java.util.function.IntPredicate;

/**
 * Created by mcaci on 4/12/17.
 */
public class ScoreWithinBoundsTest {
    /** TODO: improve code */
    public static final IntPredicate greaterThanMin = (score) -> score > Player.MIN_AUCTION_SCORE;
    public static final IntPredicate sameOrGreaterThanMin = greaterThanMin.or((score) -> (score == Player.MIN_AUCTION_SCORE));
    public static final IntPredicate lowerThanMax = (score) -> score < Player.MAX_AUCTION_SCORE;
    public static final IntPredicate sameOrLowerThanMax = lowerThanMax.or((score) -> (score == Player.MAX_AUCTION_SCORE));;
    public static final IntPredicate withinBounds = sameOrGreaterThanMin.and(sameOrLowerThanMax);

    public static boolean howIsScoreWithRespectToBounds(int score, IntPredicate predicate) {
        return predicate.test(score);
    }
}
