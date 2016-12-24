package com.msdb5.game.cardset;

import com.msdb5.game.cardset.card.Card;

import java.util.Collection;

/**
 * Created by nikiforos on 16/09/15.
 */
public abstract class CardSet<T extends Collection<Card>> {

    private final T cardSet;

    CardSet(T cardSet) {
        this.cardSet = cardSet;
    }

    public T getCardSet() {
        return this.cardSet;
    }

    public boolean isEmpty() {
        return this.cardSet.isEmpty();
    }

    public int size() {
        return cardSet.size();
    }

    @Override
    public String toString() {
        return "CardSet{" +
                "cardSet=" + this.cardSet +
                ", size=" + this.cardSet.size() +
                '}';
    }
}
