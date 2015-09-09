package game.player;

/**
 * Created by nikiforos on 08/09/15.
 */
public class MockPlayerNoSideDeck extends MockPlayer {

    public MockPlayerNoSideDeck() {
        MockHand mockHand = new MockHand(false);
        this.getHand().addCardSet(mockHand.getHand());
    }
}
