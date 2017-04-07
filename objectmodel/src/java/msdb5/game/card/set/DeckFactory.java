package msdb5.game.card.set;

import java.util.ArrayDeque;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactory extends CardSetFactory {

    public DeckFactory() {
        super(Deck.DEFAULT_DECK_SIZE);
    }

    public Deck createDeck() {
        return new Deck(new ArrayDeque<>(super.createCardSet()));
    }

}
