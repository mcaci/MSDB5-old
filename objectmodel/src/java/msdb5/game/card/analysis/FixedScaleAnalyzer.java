package msdb5.game.card.analysis;

import msdb5.game.card.Card;

/**
 * Created by nikiforos on 29/12/15.
 */
public class FixedScaleAnalyzer implements ICardAnalyzer {

    @Override
    public int analyze(Card card) {
        final int cardWeight = card.getCardNumber().getWeight();
        int cardPoints = card.getCardNumber().getPoints();
        if (cardPoints == 0) {
            cardPoints++;
        }
        return cardPoints * cardWeight;
    }
}
