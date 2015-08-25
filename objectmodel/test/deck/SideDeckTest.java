package deck;

import org.junit.Before;

import static org.junit.Assert.fail;

/**
 * Created by nikiforos on 23/08/15.
 */
public class SideDeckTest extends DeckTest {

    @Before
    public void setUp() throws Exception {
        this.setTestObjectDeck(new SideDeck());
    }

}