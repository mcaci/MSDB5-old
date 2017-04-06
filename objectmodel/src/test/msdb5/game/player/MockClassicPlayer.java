package msdb5.game.player;

import msdb5.game.card.analysis.FixedScaleAnalyzer;
import msdb5.game.card.set.Hand;
import msdb5.game.card.Card;
import msdb5.game.card.analysis.ICardAnalyzer;

import java.util.stream.Stream;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockClassicPlayer extends MockPlayer {

    public MockClassicPlayer() {
        super(0.5F, 2);
    }

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        ICardAnalyzer cardEvaluator = new FixedScaleAnalyzer();
        Stream<Card> cards = handToEvaluate.getCardSet().stream();
        return cards.mapToInt(cardEvaluator::analyze).map((int cardValue) -> (int) (cardValue /20.0) * 3).sum();
    }
}
