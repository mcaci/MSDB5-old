package game.player.auction;

import game.elements.cardset.Hand;
import game.elements.player.Player;
import game.elements.player.auction.AuctionInfo;
import game.elements.player.auction.Score;
import game.elements.player.auction.Status;
import game.elements.player.strategy.auction.IAuctionPersonality;
import game.elements.player.strategy.evaluation.hand.IHandEvaluator;

/**
 * Created by nikiforos on 10/09/15.
 */
public class AuctionPersonality_Rialzatore implements IAuctionPersonality {

    private static final int RIALZATORE_DEFAULT_SCORE = 80;

    @Override
    public Score chooseNextScore(Hand hand, int currentScore) {
        int nextScore = 0;
        if (currentScore < RIALZATORE_DEFAULT_SCORE) {
            nextScore = RIALZATORE_DEFAULT_SCORE;
        } else {
            // TODO: put appropriate implementation of IHandEvaluator and extract class
            IHandEvaluator handEvaluator = handToEvaluate -> 0;
            int handEvaluation = handEvaluator.evaluateHand(hand);
            nextScore = computeNewScore(currentScore, handEvaluation);
        }
        final Score score = new Score();
        score.setSafeScore(nextScore);
        return score;
    }

    @Override
    public AuctionInfo chooseNextStance(Player playerDeciding, int currentScore) {
        // TODO: logic is not complete (improve test)
        final AuctionInfo auctionInfo = new AuctionInfo();
        auctionInfo.setStatus(Status.IN_AUCTION);
        auctionInfo.setScore(chooseNextScore(playerDeciding.getHand(), currentScore));
        return auctionInfo;
    }

    private int computeNewScore(int currentScore, int handEvaluation) {
        int nextScore = currentScore;
        nextScore++; // TODO: change logic (and include handEvaluation)
        return nextScore;
    }

}
