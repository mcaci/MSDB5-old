package game.elements.player.mock.characteristic;

import game.elements.player.characteristic.IAuctionPersonality;
import game.elements.player.info.AuctionInfo;
import game.elements.player.info.AuctionScore;
import game.elements.player.info.AuctionStatus;
import game.elements.player.mock.MockClassicPlayer;
import game.elements.player.mock.MockCowardPlayer;
import game.elements.player.mock.MockUnwaveringPlayer;
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

    private static final int CURRENT_SCORE = 60;

    private IAuctionPersonality iAuctionPersonalityTestObject;

    private Class<?> implClass;

    public IAuctionPersonalityTest(Class<?> implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection<?> initParameters() {
        return Arrays.asList(new Object[][]{
                {MockClassicPlayer.class},
                {MockUnwaveringPlayer.class},
                {MockCowardPlayer.class}
        });
    }

    @Before
    public void setUp() throws Exception {
        Constructor<?> constructor = implClass.getConstructor();
        iAuctionPersonalityTestObject = (IAuctionPersonality) constructor.newInstance();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Auction personality tested in " + this.iAuctionPersonalityTestObject);
    }

    @Test
    public void testValidity() throws Exception {

        AuctionInfo infoAfterAction = iAuctionPersonalityTestObject.performAuctionAction(CURRENT_SCORE);
        assertNotNull(infoAfterAction);

        AuctionStatus statusAfterAction = infoAfterAction.getAuctionStatus();
        AuctionScore scoreAfterAction = infoAfterAction.getAuctionScore();
        if (!statusAfterAction.actionWasDone()) {
            fail("Action should always be taken");
        } else if (statusAfterAction.hasFolded()) {
            assertTrue(CURRENT_SCORE == scoreAfterAction.getScore());
        } else {
            assertTrue(CURRENT_SCORE < scoreAfterAction.getScore());
        }

    }


}