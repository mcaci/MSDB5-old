package game.table;

import game.factory.GameTableFactory;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 08/09/15.
 */
public class MockGameTable extends GameTable {

    public MockGameTable() {
        super();
        GameTableFactory gameTableFactory = new GameTableFactory();
        GameTable gameTableCreated = gameTableFactory.getCreatedGameTable();
        this.setDeck(gameTableCreated.getDeck());
        this.setPlayers(gameTableCreated.getPlayers());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("MockGameTable: " + this);
    }

    @Test
    public void testValidity() throws Exception {
        assertNotNull(this.getDeck());
        assertNotNull(this.getPlayers());
    }
}
