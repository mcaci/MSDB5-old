package com.msdb5.game.player.mock.characteristic;

import com.msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import com.msdb5.game.player.characteristic.IPersonalityForPreparation;
import com.msdb5.game.player.info.AuctionInfo;
import com.msdb5.game.player.info.AuctionScore;
import com.msdb5.game.player.info.AuctionStatus;
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
public class IPersonalityForPreparationTest {

    private final static byte[] SCORES_TO_TEST = {0, 60, 89, 119, 120};

    private IPersonalityForPreparation iPersonalityForPreparationTestObject;
    private byte currentAuctionScore;

    private Class<?> implClass;

    public IPersonalityForPreparationTest(Class<?> implClass, byte currentAuctionScore) {
        this.implClass = implClass;
        this.currentAuctionScore = currentAuctionScore;
    }

    @Parameterized.Parameters
    public static Collection<?> initParameters() {

        Object[][] params = new Object[CommonData.PERS_IMPL_CLASSES.length * SCORES_TO_TEST.length][];
        int i = 0;
        for (Class personalityClass : CommonData.PERS_IMPL_CLASSES) {
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
        iPersonalityForPreparationTestObject = (IPersonalityForPreparation) constructor.newInstance();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Auction personality tested with personality " + iPersonalityForPreparationTestObject.getClass().getSimpleName() + " in " + this.iPersonalityForPreparationTestObject);
        System.out.println("And with starting score of " + currentAuctionScore);
    }

    @Test
    public void testValidity() throws Exception {

        AuctionInfo infoAfterAction = null;
        boolean raisedIncorrectly = false;
        try {
            infoAfterAction = iPersonalityForPreparationTestObject.performAuctionAction(currentAuctionScore);
        } catch (AuctionOnScoreOutOfBoundsException ex) {
            raisedIncorrectly = currentAuctionScore < AuctionScore.MIN_SCORE || currentAuctionScore >= AuctionScore.MAX_SCORE;
        }
        if (raisedIncorrectly) {
            assertNull(infoAfterAction);
        } else {
            assertNotNull(infoAfterAction);
            AuctionStatus statusAfterAction = infoAfterAction.getAuctionStatus();
            AuctionScore scoreAfterAction = infoAfterAction.getAuctionScore();
            if (!statusAfterAction.actionWasDone()) {
                fail("Action should always be taken");
            } else if (statusAfterAction.hasFolded()) {
                assertTrue(currentAuctionScore >= scoreAfterAction.getScore());
            } else {
                assertTrue(currentAuctionScore < scoreAfterAction.getScore());
            }
        }
    }

}