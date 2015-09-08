package game.factory;

import game.elements.Card;
import game.elements.Deck;

import java.util.List;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactory extends CardSetFactory {

    public DeckFactory() {
        super(Deck.DEFAULT_SIZE);
    }

    public Deck createDeck() {
        Deck createdDeck = new Deck();
        List<Card> localDeck = createCardSet();
        shuffleCardSet(localDeck);
        createdDeck.addCardSet(localDeck, this.setSize);
        return createdDeck;
    }
}
