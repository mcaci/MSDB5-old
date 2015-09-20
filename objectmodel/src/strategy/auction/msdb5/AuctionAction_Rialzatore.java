package strategy.auction.msdb5;

import game.elements.cardset.Hand;
import game.player.Player;
import game.player.auction.AuctionStance;
import game.player.auction.Score;
import game.player.auction.Status;
import strategy.auction.IAuctionAction;
import strategy.evaluation.hand.IHandEvaluator;

/**
 * Created by nikiforos on 10/09/15.
 */
public class AuctionAction_Rialzatore implements IAuctionAction {

    private static final int RIALZATORE_DEFAULT_SCORE = 80;

    @Override
    public Score chooseNextScore(Hand hand, int currentScore) {
        int nextScore = currentScore;
        if (currentScore < RIALZATORE_DEFAULT_SCORE) {
            nextScore = RIALZATORE_DEFAULT_SCORE;
        } else {
            // TODO: put appropriate implementation of IHandEvaluator and extract class
            IHandEvaluator handEvaluator = new IHandEvaluator() {
                @Override
                public int evaluateHand(Hand handToEvaluate) {
                    return 0;
                }

            };
            int handEvaluation = handEvaluator.evaluateHand(hand);
            nextScore = computeNewScore(currentScore, handEvaluation);
        }
        final Score score = new Score();
        score.setSafeScore(nextScore);
        return score;
    }

    @Override
    public AuctionStance chooseNextStance(Player playerDeciding, int currentScore) {
        // TODO: logic is not complete (improve test)
        final AuctionStance auctionStance = new AuctionStance();
        auctionStance.setStatus(Status.IN_AUCTION);
        auctionStance.setScore(chooseNextScore(playerDeciding.getHand(), currentScore));
        return auctionStance;
    }

    private int computeNewScore(int currentScore, int handEvaluation) {
        int nextScore = currentScore;
        nextScore++; // TODO: change logic (and include handEvaluation)
        return nextScore;
    }

}
