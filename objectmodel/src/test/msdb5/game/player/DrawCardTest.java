package msdb5.game.player;

import msdb5.game.card.Card;
import msdb5.game.card.set.Deck;
import msdb5.game.card.set.DeckFactoryTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mcaci on 4/19/17.
 */
public class DrawCardTest {

    private Player mockPlayer;
    private Deck mockDeck;

    @Before
    public void setUp() throws Exception {
        mockPlayer = new MockClassicPlayer();
        mockDeck = new DeckFactoryTest().getMockDeck();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Player tested: " + this.mockPlayer);
        System.out.println("Deck tested: " + this.mockDeck);
    }

    @Test
    public void testPlayerHasDrawnCard() {
        Card toDraw = mockDeck.getCardSet().peek();
        mockPlayer.drawCard(mockDeck);
        assertTrue(mockPlayer.getHand().contains(toDraw));
    }

    @Test
    public void testCardIsNotPresentInDeckAfterPlayerDraws() {
        Card toDraw = mockDeck.getCardSet().peek();
        mockPlayer.drawCard(mockDeck);
        assertFalse(mockDeck.contains(toDraw));
    }
}
