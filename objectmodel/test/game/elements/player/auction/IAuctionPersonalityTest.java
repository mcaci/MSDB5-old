package game.elements.player.auction;

import game.elements.cardset.Hand;
import game.elements.player.MockPlayer;
import game.elements.player.MockUnwaveringPlayer;
import game.elements.player.Player;
import game.elements.player.auction.info.AuctionInfo;
import game.elements.player.auction.info.AuctionScore;
import game.elements.player.auction.info.AuctionStatus;
import game.factory.cardset.HandFactoryTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class IAuctionPersonalityTest {

    private int CURRENT_SCORE = 60;
    private IAuctionPersonality iAuctionPersonalityTestObject;

    private Class<?> implClass;
    private Hand inputHand;
    private Player inputPlayer;

    public IAuctionPersonalityTest(Class<?> implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection<?> initParameters() {
        return Arrays.asList(new Object[][]{
                {MockPlayer.class},
                {MockUnwaveringPlayer.class}
        });
    }

    @Before
    public void setUp() throws Exception {
        inputHand = new HandFactoryTest(true).getMockHand();
        inputPlayer = new MockPlayer();
        Constructor<?> constructor = implClass.getConstructor();
        iAuctionPersonalityTestObject = (IAuctionPersonality) constructor.newInstance();
    }

    @Test
    public void testChooseNextStance() throws Exception {
        AuctionStatus beforeAuctionStatus = inputPlayer.getAuctionInfo().getAuctionStatus();
        AuctionScore beforeAuctionScore = inputPlayer.getAuctionInfo().getAuctionScore();
        // TODO: verify validity of test
        AuctionInfo nextStance = iAuctionPersonalityTestObject.performAuctionAction(CURRENT_SCORE);
        assertNotNull(nextStance);
        AuctionStatus afterAuctionStatus = nextStance.getAuctionStatus();
        AuctionScore afterAuctionScore = nextStance.getAuctionScore();

        checkScoreAndStatus(beforeAuctionStatus, beforeAuctionScore, afterAuctionStatus, afterAuctionScore);
    }

    private void checkScoreAndStatus(AuctionStatus beforeAuctionStatus, AuctionScore beforeAuctionScore, AuctionStatus afterAuctionStatus, AuctionScore afterAuctionScore) {
        String statusMessage = "<" + beforeAuctionStatus + ", " + afterAuctionStatus + ">";
        String scoreMessage = "<" + beforeAuctionScore + ", " + afterAuctionScore + ">";
        boolean statusCheck;
        boolean scoreCheck;
        switch (beforeAuctionStatus) {
            case FOLDED:
                statusCheck = afterAuctionStatus.hasFolded();
                scoreCheck = afterAuctionScore.compareTo(beforeAuctionScore) == 0;
                break;
            default:
                statusCheck = afterAuctionStatus.actionWasDone();
                if (afterAuctionStatus.hasFolded()) {
                    scoreCheck = afterAuctionScore.compareTo(beforeAuctionScore) == 0;
                } else {
                    scoreCheck = afterAuctionScore.compareTo(beforeAuctionScore) > 0;
                }
                break;
        }
        assertTrue(statusMessage, statusCheck);
        assertTrue(scoreMessage, scoreCheck);
    }

    @Test
    public void testChooseNextScore() throws Exception {
        // TODO: verify validity of test
        AuctionInfo nextAuctionInfo = iAuctionPersonalityTestObject.performAuctionAction(CURRENT_SCORE);
        assertTrue(nextAuctionInfo.getAuctionScore().getScore() + " is not greater than " + CURRENT_SCORE, nextAuctionInfo.getAuctionScore().getScore() >
                CURRENT_SCORE);
    }
}