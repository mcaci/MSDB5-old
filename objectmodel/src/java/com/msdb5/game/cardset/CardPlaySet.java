package com.msdb5.game.cardset;

import com.msdb5.game.cardset.card.Card;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by nikiforos on 24/12/16.
 */
public abstract class CardPlaySet<T extends Collection<Card>> extends CardSet {

    CardPlaySet() {
        super(new LinkedList<>());
    }

    CardPlaySet(Collection<Card> cardSet) {
        this();
        this.getCardSet().addAll(cardSet);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
