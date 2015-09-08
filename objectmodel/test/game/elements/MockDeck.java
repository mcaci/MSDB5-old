package game.elements;

import game.factory.DeckFactory;

/**
 * Created by nikiforos on 07/09/15.
 */
public class MockDeck extends Deck {

    public MockDeck() {
        DeckFactory deckFactory = new DeckFactory();
        Deck createdDeck = deckFactory.createDeck();
        this.getDeck().addAll(createdDeck.getDeck());
    }

}
