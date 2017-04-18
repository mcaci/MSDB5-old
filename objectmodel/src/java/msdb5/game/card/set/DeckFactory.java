package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactory extends CardSetFactory {

    public DeckFactory() {
        super(ArrayDeque<Card>::new, Deck.DEFAULT_DECK_SIZE);
    }

    @Override
    public Deck create() {
        return new Deck( (Queue<Card>) this.createCardSet());
    }
}
