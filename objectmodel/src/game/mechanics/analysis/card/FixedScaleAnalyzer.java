package game.mechanics.analysis.card;

import game.cardset.card.Card;

/**
 * Created by nikiforos on 29/12/15.
 */
public class FixedScaleAnalyzer implements ICardAnalyzer {

    @Override
    public int analyze(Card card) {
        final int weight = card.getCardNumber().getWeight();
        int points = card.getCardNumber().pointsForTheCard();
        if (points == 0) {
            points++;
        }
        return points * weight;
    }
}
