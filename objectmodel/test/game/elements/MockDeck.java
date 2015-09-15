package game.elements;

import game.factory.DeckFactory;

/**
 * TODO: still unused, does it make sense?
 * Created by nikiforos on 07/09/15.
 */
public class MockDeck extends Deck {

    public MockDeck() {
        DeckFactory deckFactory = new DeckFactory();
        Deck createdDeck = deckFactory.createDeck();
        this.getDeck().addAll(createdDeck.getDeck());
    }

}
