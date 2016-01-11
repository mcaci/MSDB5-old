package com.msdb5.game.player.mock.characteristic;

import com.msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import com.msdb5.game.player.characteristic.IAuctionPersonality;
import com.msdb5.game.player.info.AuctionInfo;
import com.msdb5.game.player.info.AuctionScore;
import com.msdb5.game.player.info.AuctionStatus;
import com.msdb5.game.player.mock.MockClassicPlayer;
import com.msdb5.game.player.mock.MockCowardPlayer;
import com.msdb5.game.player.mock.MockUnwaveringPlayer;
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
public class IAuctionPersonalityTest {

    private final static Class[] PERS_IMPL_CLASSES = {MockClassicPlayer.class, MockCowardPlayer.class, MockUnwaveringPlayer.class};
    private final static byte[] SCORES_TO_TEST = {0, 60, 89, 119, 120};

    private IAuctionPersonality iAuctionPersonalityTestObject;
    private int startingScore;


    private Class<?> implClass;

    public IAuctionPersonalityTest(Class<?> implClass, byte startingScore) {
        this.implClass = implClass;
        this.startingScore = startingScore;
    }

    @Parameterized.Parameters
    public static Collection<?> initParameters() {

        Object[][] params = new Object[PERS_IMPL_CLASSES.length * SCORES_TO_TEST.length][];
        int i = 0;
        for (Class personalityClass : PERS_IMPL_CLASSES) {
            for (byte score : SCORES_TO_TEST) {
                params[i] = new Object[2];
                params[i][0] = personalityClass;
                params[i][1] = score;
                i++;
            }
        }
        return Arrays.asList(params);
    }

    @Before
    public void setUp() throws Exception {
        Constructor<?> constructor = implClass.getConstructor();
        iAuctionPersonalityTestObject = (IAuctionPersonality) constructor.newInstance();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Auction personality tested with personality " + iAuctionPersonalityTestObject.getClass().getSimpleName() + " in " + this.iAuctionPersonalityTestObject);
        System.out.println("And with starting score of " + startingScore);
    }

    @Test
    public void testValidity() throws Exception {

        AuctionInfo infoAfterAction = null;
        boolean raisedCorrectly = false;
        try {
            infoAfterAction = iAuctionPersonalityTestObject.performAuctionAction(startingScore);
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