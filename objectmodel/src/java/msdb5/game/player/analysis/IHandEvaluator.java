package msdb5.game.player.analysis;

import msdb5.game.card.set.Hand;

/**
 * Created by mcaci on 4/7/17.
 */
@FunctionalInterface
public interface IHandEvaluator {

    int evaluate(Hand hand);

}

