package game.player;

/**
 * Created by nikiforos on 08/09/15.
 */
public class MockPlayerWithSideDeck extends MockPlayer {

    public MockPlayerWithSideDeck() {
        MockHand mockHand = new MockHand(true);
        this.getHand().addCardSet(mockHand.getHand());
    }
}
