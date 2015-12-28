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
public class AuctionPersonality_Ambiguo implements IAuctionPersonality {

    @Override
    public Score chooseNextScore(Hand hand, int currentScore) {
        int nextScore = currentScore;
        // TODO: put appropriate implementation of IHandEvaluator and extract class
        IHandEvaluator handEvaluator = new IHandEvaluator() {
            @Override
            public int evaluateHand(Hand handToEvaluate) {
                return 0;
            }

        };
        int handEvaluation = handEvaluator.evaluateHand(hand);
        nextScore = decideNextScore(currentScore, handEvaluation);

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

    private int decideNextScore(int currentScore, int handEvaluation) {
        int nextScore = currentScore;
        nextScore++; // TODO: change logic (and include handEvaluation)
        return nextScore;
    }
}
