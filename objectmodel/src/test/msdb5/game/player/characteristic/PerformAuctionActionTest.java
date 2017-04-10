package msdb5.game.player.characteristic;

import msdb5.game.player.MockClassicPlayer;
import msdb5.game.player.info.AuctionInfo;
import msdb5.game.player.info.AuctionScore;
import msdb5.game.player.info.AuctionStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class PerformAuctionActionTest {

    private final static byte[] SCORES_TO_TEST = {0, 60, 89, 119, 120};

    private IPersonalityForPreparation iPersonalityForPreparationTestObject;
    private int startingScore;

    public PerformAuctionActionTest(byte startingScore) {
        this.startingScore = startingScore;
    }

    @Parameterized.Parameters
    public static Collection<?> initParameters() {

        Object[][] params = new Object[SCORES_TO_TEST.length][];
        int i = 0;
            for (byte score : SCORES_TO_TEST) {
                params[i] = new Object[1];
                params[i][0] = score;
                i++;
        }
        return Arrays.asList(params);
    }

    @Before
    public void setUp() throws Exception {
        iPersonalityForPreparationTestObject = new MockClassicPlayer();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Auction personality tested with personality " + iPersonalityForPreparationTestObject.getClass().getSimpleName() + " in " + this.iPersonalityForPreparationTestObject);
        System.out.println("And with starting score of " + startingScore);
    }

    @Test
    public void testValidity() throws Exception {

        AuctionInfo infoAfterAction = null;
        boolean raisedCorrectly = false;
        try {
            infoAfterAction = iPersonalityForPreparationTestObject.performAuctionAction(startingScore);
        } catch (AuctionOnScoreOutOfBoundsException ex) {
            raisedCorrectly = startingScore < AuctionScore.MIN_SCORE || startingScore >= AuctionScore.MAX_SCORE;
        }
        if (raisedCorrectly) {
            assertNull(infoAfterAction);
        } else {
            assertNotNull(infoAfterAction);
            AuctionStatus statusAfterAction = infoAfterAction.getAuctionStatus();
            AuctionScore scoreAfterAction = infoAfterAction.getAuctionScore();
            if (!statusAfterAction.actionWasDone()) {
                fail("Action should always be taken");
            } else if (statusAfterAction.hasFolded()) {
                assertTrue(startingScore >= scoreAfterAction.getScore());
            } else {
                assertTrue(startingScore < scoreAfterAction.getScore());
            }
        }
    }

}