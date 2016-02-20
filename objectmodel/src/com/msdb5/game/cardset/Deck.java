package com.msdb5.game.cardset;

import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.cardset.card.CardNumber;
import com.msdb5.game.cardset.card.CardSuit;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by nikiforos on 23/08/15.
 */
public class Deck extends CardSet<Queue<Card>> {

    public static final int DECK_DEFAULT_SIZE = CardNumber.values().length * CardSuit.values().length;

    public Deck(Collection<Card> deckCards) {
        super(new LinkedList<>());
        this.getCardSet().addAll(deckCards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                super.toString() +
                "}";
    }
}
