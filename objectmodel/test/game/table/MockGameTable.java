package game.table;

import game.elements.Card;
import game.factory.GameTableFactory;
import game.player.Player;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 08/09/15.
 */
@RunWith(Parameterized.class)
public class MockGameTable extends GameTable {

    public MockGameTable(boolean withSideDeck) {
        super();
        createMockTable();
        prepareMockTable(withSideDeck);
    }

    @Parameterized.Parameters
    public static Collection initParameters() {
        return Arrays.asList(new Object[][]{
                {true},
                {false}
        });
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("MockGameTable: " + this);
    }

    private void createMockTable() {
        GameTableFactory gameTableFactory = new GameTableFactory();
        GameTable gameTableCreated = gameTableFactory.getCreatedGameTable();
        this.setDeck(gameTableCreated.getDeck());
        this.setPlayers(gameTableCreated.getPlayers());
    }

    public void prepareMockTable(boolean withSideDeck) {
        Iterator<Card> cardsToDistribute = this.getDeck().getCardSet().iterator();
        boolean keepDistributing = true;
        int playerCounter = 0;
        while (keepDistributing && cardsToDistribute.hasNext()) {
            distributeCard(cardsToDistribute.next(), playerCounter);
            playerCounter++;
            if (withSideDeck && this.getDeck().getCardSet().size() <= 5) {
                keepDistributing = false;
            }
        }
    }

    private void distributeCard(Card nextCard, int playerCounter) {
        int playerIndex = playerCounter % this.getPlayers().length;
        Player player = this.getPlayers()[playerIndex];
        player.getHand().add(nextCard);
    }

    @Test
    public void testValidity() throws Exception {
        assertNotNull(this.getDeck());
        assertNotNull(this.getPlayers());
        for (int i = 0; i < this.getPlayers().length; i++) {
            assertTrue(this.getPlayers()[i].getHand().getCardSet().size() > 0);
        }
    }

}
