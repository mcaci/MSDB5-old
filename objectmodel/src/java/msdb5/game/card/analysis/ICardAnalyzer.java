package msdb5.game.card.analysis;

import msdb5.game.card.Card;

/**
 * Created by nikiforos on 10/09/15.
 */
@FunctionalInterface
public interface ICardAnalyzer {

    int analyze(Card card);
}
