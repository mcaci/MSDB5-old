package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.Set;

/**
 * Created by nikiforos on 29/08/15.
 */
public class Hand extends CardSet<Set<Card>> {

    public static final int WITH_SIDE_DECK_HAND_SIZE = 7;
    public static final int WITHOUT_SIDE_DECK_HAND_SIZE = 8;

    Hand(Set<Card> handCards) {
        super(handCards);
    }

    public void add(Card card) {
        this.getCardSet().add(card);
    }

    @Override
    public String toString() {
        return "Hand{" +
                super.toString() +
                '}';
    }
}
