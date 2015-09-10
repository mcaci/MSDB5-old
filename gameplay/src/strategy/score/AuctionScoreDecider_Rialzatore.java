package strategy.score;

import game.player.Hand;
import strategy.evaluation.hand.IHandEvaluator;

/**
 * Created by nikiforos on 10/09/15.
 */
public class AuctionScoreDecider_Rialzatore implements IAuctionScoreDecider {

    private static final int RIALZATORE_DEFAULT_SCORE = 80;

    @Override
    public int chooseNextScore(Hand hand, int currentScore) {
        int nextScore = currentScore;
        if (currentScore < RIALZATORE_DEFAULT_SCORE) {
            nextScore = RIALZATORE_DEFAULT_SCORE;
        } else {
            // TODO: put appropriate implementation of IHandEvaluator
            IHandEvaluator handEvaluator = new IHandEvaluator() {
                @Override
                public int evaluateHand(Hand handToEvaluate) {
                    return 0;
                }

            };
            int handEvaluation = handEvaluator.evaluateHand(hand);
            nextScore = computeNewScore(currentScore, handEvaluation);
        }
        return nextScore;
    }

    private int computeNewScore(int currentScore, int handEvaluation) {
        // TODO: complete code
        return 0;
    }

}
