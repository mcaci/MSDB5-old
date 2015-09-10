package strategy.score;

import game.player.Hand;

/**
 * Created by nikiforos on 10/09/15.
 */
public interface IAuctionScoreDecider {

    int chooseNextScore(Hand hand, int currentScore);

}
