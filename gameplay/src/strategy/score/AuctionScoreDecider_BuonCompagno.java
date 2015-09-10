package strategy.score;

import game.player.Hand;
import strategy.evaluation.hand.IHandEvaluator;

/**
 * Created by nikiforos on 10/09/15.
 */
public class AuctionScoreDecider_BuonCompagno implements IAuctionScoreDecider {

    @Override
    public int chooseNextScore(Hand hand, int currentScore) {
        int nextScore = currentScore;
        // TODO: put appropriate implementation of IHandEvaluator
        IHandEvaluator handEvaluator = new IHandEvaluator() {
            @Override
            public int evaluateHand(Hand handToEvaluate) {
                return 0;
            }

        };
        int handEvaluation = handEvaluator.evaluateHand(hand);
        nextScore = computeNewScore(currentScore, handEvaluation);
        return nextScore;
    }

    private int computeNewScore(int currentScore, int handEvaluation) {
        // TODO: complete code
        return 0;
    }
}
