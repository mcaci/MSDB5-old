package game.player.mock;

import game.cardset.Hand;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockCowardPlayer extends MockPlayer {

    public MockCowardPlayer() {
        super(1F, 0);
    }

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        return 60;
    }
}
