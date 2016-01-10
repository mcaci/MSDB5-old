package game.player.mock;

import game.cardset.Hand;
import game.cardset.card.Card;
import game.mechanics.analysis.card.FixedScaleAnalyzer;
import game.mechanics.analysis.card.ICardAnalyzer;

import java.util.Collection;
import java.util.Iterator;

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
        Collection<Card> cards = handToEvaluate.getCardSet();
        int handValue = 0;
        Iterator<Card> cardIterator = cards.iterator();
        while (cardIterator.hasNext()) {
            int cardValue = cardEvaluator.analyze(cardIterator.next());
            handValue += cardValue;
        }

        int weightedHandValue = (int) (handValue / 20.0) * 3;
        return weightedHandValue;
    }
}
