package msdb5.game.player.characteristic;

import msdb5.game.card.set.Hand;

import java.util.function.ToIntFunction;

/**
 * Created by mcaci on 4/7/17.
 */
@FunctionalInterface
public interface IHandEvaluator extends ToIntFunction<Hand>{}
