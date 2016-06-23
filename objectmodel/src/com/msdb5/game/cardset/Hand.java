package com.msdb5.game.cardset;

import com.msdb5.game.cardset.card.Card;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by nikiforos on 29/08/15.
 */
public class Hand extends CardSet {

    public static final int EMPTY_HAND_SIZE = 0;
    public static final int WITH_SIDE_DECK_HAND_SIZE = 7;
    public static final int WITHOUT_SIDE_DECK_HAND_SIZE = 8;

    public Hand(Collection<Card> deckCards) {
        super(new LinkedList<Card>());
        this.getCardSet().addAll(deckCards);
    }

    public void add(Card card) {
        this.getCardSet().add(card);
    }

}
