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
public class Deck extends CardSet {

    public static final int DECK_DEFAULT_SIZE = CardNumber.values().length * CardSuit.values().length;

    public Deck(Collection<Card> deckCards) {
        super(new LinkedList<Card>());
        this.getCardSet().addAll(deckCards);
    }

    public Card drawCard() {
        Card card = null;
        Queue<Card> deck = null;
        if(this.getCardSet() instanceof Queue){
            deck = (Queue<Card>) (this.getCardSet());
            card = deck.remove();
        };
        return card;
    }

}
