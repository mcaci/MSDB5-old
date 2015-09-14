package strategy.evaluation.hand;

import game.elements.Card;
import game.player.Hand;
import strategy.evaluation.card.DummyCardEvaluator;

import java.util.List;

/**
 * Created by nikiforos on 10/09/15.
 */
public class SumEvaluator implements IHandEvaluator {

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        DummyCardEvaluator cardEvaluator = new DummyCardEvaluator();
        List<Card> cards = handToEvaluate.getHand();
        int handValue = 0;
        for (int i = 0; i < cards.size(); i++) {
            int cardValue = cardEvaluator.evaluateCard(cards.get(i));
            handValue += cardValue;
        }
        return handValue;
    }
}
