package msdb5.game.player.characteristic;

import msdb5.game.player.MockClassicPlayer;
import msdb5.game.player.MockCowardPlayer;
import msdb5.game.player.MockUnwaveringPlayer;
import msdb5.game.player.Player;
import msdb5.game.player.info.AuctionInfo;
import msdb5.game.player.info.AuctionStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class PerformAuctionActionTest {

    private final static byte[] SCORES_TO_TEST = {0, 60, 89, 119, 120};
    private static final Class[] PLAYERS_CLASSES = {MockClassicPlayer.class, MockCowardPlayer.class, MockUnwaveringPlayer.class};

    private Player player;
    private int startingScore;

    private Class<?> implClass;

    public PerformAuctionActionTest(Class<?> implClass, byte startingScore) {
        this.implClass = implClass;
        this.startingScore = startingScore;
    }

    @Parameterized.Parameters
    public static Collection<?> initParameters() {


        Object[][] params = new Object[PLAYERS_CLASSES.length * SCORES_TO_TEST.length][];
        int i = 0;
        for (Class players : PLAYERS_CLASSES) {
            for (byte score : SCORES_TO_TEST) {
                params[i] = new Object[2];
                params[i][0] = players;
                params[i][1] = score;
                i++;
            }
        }
        return Arrays.asList(params);
    }

    @Before
    public void setUp() throws Exception {
        Constructor<?> constructor = implClass.getConstructor();
        player = (Player) constructor.newInstance();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Auction personality tested with personality " + player.getClass().getSimpleName() + " in " + this.player);
        System.out.println("And with starting score of " + startingScore);
    }

    @Test
    public void testValidity() throws Exception {

        AuctionInfo infoAfterAction = null;
        boolean exceptionWasRaisedCorrectly = false;
        try {
            infoAfterAction = player.performAuctionAction(startingScore);
        } catch (AuctionOnScoreOutOfBoundsException ex) {
            exceptionWasRaisedCorrectly = startingScore >= Player.MAX_AUCTION_SCORE || startingScore < Player.MIN_AUCTION_SCORE;
        }
        if (exceptionWasRaisedCorrectly) {
            assertNull(infoAfterAction);
        } else {
            assertNotNull(infoAfterAction);
            verifyThatActionWasTaken(infoAfterAction);
        }
    }

    private void verifyThatActionWasTaken(AuctionInfo infoAfterAction) {
        AuctionStatus statusAfterAction = infoAfterAction.getAuctionStatus();
        int scoreAfterAction = infoAfterAction.getAuctionScore();
        if (statusAfterAction.actionWasDone()) {
            verifyScoreValidity(statusAfterAction, scoreAfterAction);
        } else {
            fail("Action should always be taken");
        }
    }

    private void verifyScoreValidity(AuctionStatus statusAfterAction, int scoreAfterAction) {
        if (statusAfterAction.hasFolded()) {
            assertTrue(startingScore >= scoreAfterAction);
        } else {
            assertTrue(startingScore < scoreAfterAction);
        }
    }

}