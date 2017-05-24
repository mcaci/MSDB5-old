package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.Set;

/**
 * Created by nikiforos on 29/08/15.
 */
public class Hand extends CardSet<Set<Card>> {

    Hand(Set<Card> handCards) {
        super(handCards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                super.toString() +
                '}';
    }
}
