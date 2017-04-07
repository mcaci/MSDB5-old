package msdb5.game.player.analysis;

import msdb5.game.card.Card;
import msdb5.game.card.analysis.FixedScaleAnalyzer;
import msdb5.game.card.analysis.ICardAnalyzer;
import msdb5.game.card.set.Hand;

import java.util.stream.Stream;

/**
 * Created by mcaci on 4/7/17.
 */
public class SimpleHandEvaluator implements IHandEvaluator {

    private static final double WEIGHT = 3.0/20.0;

    @Override
    public int evaluate(Hand hand) {
        ICardAnalyzer cardEvaluator = new FixedScaleAnalyzer();
        Stream<Card> cards = hand.getCardSet().stream();
        return cards.mapToInt(cardEvaluator::analyze).map(cardValue -> (int) (cardValue * WEIGHT)).sum();
    }
}
