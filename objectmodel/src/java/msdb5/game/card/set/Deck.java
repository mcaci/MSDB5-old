package msdb5.game.card.set;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;

import java.util.Queue;

/**
 * Created by nikiforos on 23/08/15.
 */
public class Deck extends CardSet<Queue<Card>> {

    public static final int DEFAULT_DECK_SIZE = CardNumber.values().length * CardSuit.values().length;

    Deck(Queue<Card> deckCards) {
        super(deckCards);
    }

    public Card giveOneCard() {
        return this.getCardSet().poll();
    }

    @Override
    public String toString() {
        return "Deck{" +
                super.toString() +
                "}";
    }
}
