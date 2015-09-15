package game.factory;

import game.elements.Deck;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactory extends CardSetFactory {

    public DeckFactory() {
        super(Deck.DEFAULT_SIZE);
    }

    public Deck createDeck() {
        return new Deck(createShuffledCardSet(), this.setSize);
    }
}
