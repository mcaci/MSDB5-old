package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikiforos on 29/08/15.
 */
public class Hand extends CardSet<List<Card>> {

    public static final int WITH_SIDE_DECK_HAND_SIZE = 7;
    public static final int WITHOUT_SIDE_DECK_HAND_SIZE = 8;
    public static final int EMPTY_HAND_SIZE = 0;

    public Hand() {
        this(new ArrayList<>(EMPTY_HAND_SIZE));
    }

    public Hand(List<Card> handCards) {
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
