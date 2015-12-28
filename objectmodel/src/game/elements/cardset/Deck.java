package game.elements.cardset;

import game.elements.base.Card;
import game.elements.base.CardNumber;
import game.elements.base.CardSuit;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by nikiforos on 23/08/15.
 */
public class Deck extends CardSet<Queue<Card>> {

    public static final int DECK_DEFAULT_SIZE = CardNumber.values().length * CardSuit.values().length;

    Deck() {
        super(new LinkedList<>());
    }

    public Deck(Collection<Card> deckCards) {
        this();
        this.getCardSet().addAll(deckCards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                super.toString() +
                "}";
    }
}
