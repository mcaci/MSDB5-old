package com.msdb5.game.cardset;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.msdb5.game.cardset.card.Card;

/**
 * Created by nikiforos on 29/08/15.
 */
public class Hand extends CardSet<List<Card>> {

    public static final int WITH_SIDE_DECK_HAND_SIZE = 7;
    public static final int WITHOUT_SIDE_DECK_HAND_SIZE = 8;

    public Hand(Collection<Card> deckCards) {
        this();
        this.getCardSet().addAll(deckCards);
    }

    public Hand() {
        super(new LinkedList<>());
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
