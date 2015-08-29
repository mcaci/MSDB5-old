package deck;

import deck.factory.RandomDeckFactory;
import org.junit.Before;

/**
 * Created by nikiforos on 23/08/15.
 */
public class DeckTest {

    private Deck testObjectDeck;

    public void setTestObjectDeck(Deck testObjectDeck) {
        this.testObjectDeck = testObjectDeck;
    }

    @Before
    public void setUp() throws Exception {
        final RandomDeckFactory randomDeckFactory = new RandomDeckFactory(40);
        this.setTestObjectDeck(randomDeckFactory.getCreatedDeck());
    }


}