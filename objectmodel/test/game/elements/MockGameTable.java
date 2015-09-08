package game.elements;

import game.factory.GameTableFactory;

/**
 * Created by nikiforos on 08/09/15.
 */
public class MockGameTable extends GameTable {

    public MockGameTable() {
        GameTableFactory gameTableFactory = new GameTableFactory();
        GameTable gameTableCreated = gameTableFactory.getCreatedGameTable();
        this.setDeck(gameTableCreated.getDeck());
        this.setPlayers(gameTableCreated.getPlayers());
    }
}
