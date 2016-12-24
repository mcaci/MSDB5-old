package com.msdb5.game.cardset;

import com.msdb5.game.cardset.card.Card;

import java.util.Collection;
import java.util.List;

/**
 * Created by nikiforos on 29/08/15.
 */
public class Hand extends CardPlaySet<List<Card>> {

    public static final int WITH_SIDE_DECK_HAND_SIZE = 7;
    public static final int WITHOUT_SIDE_DECK_HAND_SIZE = 8;
    public static final int EMPTY_HAND_SIZE = 0;

    public Hand() {
        super();
    }

    public Hand(Collection<Card> handCards) {
        super(handCards);
    }

    public void add(Card card) {
        this.getCardSet().add(card);
    }

    @Override
    public List<Card> getCardSet() {
        return (List<Card>) super.getCardSet();
    }

    @Override
    public String toString() {
        return "Hand{" +
                super.toString() +
                '}';
    }
}
