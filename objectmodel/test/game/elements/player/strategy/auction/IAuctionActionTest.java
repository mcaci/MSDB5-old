package game.elements.player.strategy.auction;

import game.elements.cardset.Hand;
import game.elements.cardset.MockHand;
import game.elements.player.MockPlayer;
import game.elements.player.Player;
import game.elements.player.auction.AuctionInfo;
import game.elements.player.auction.Score;
import game.elements.player.auction.Status;
import game.elements.player.strategy.auction.msdb5.*;
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
public class IAuctionActionTest {

    private int CURRENT_SCORE = 60;
    private IAuctionAction iAuctionActionTestObject;

    private Class<?> implClass;
    private Hand inputHand;
    private Player inputPlayer;

    public IAuctionActionTest(Class<?> implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection<?> initParameters() {
        return Arrays.asList(new Object[][]{
                {MockAuctionAction.class},
                {MockUnwaveringAuctionAction.class},
                {AuctionAction_Rialzatore.class},
                {AuctionAction_CampioneDecaduto.class},
                {AuctionAction_BuonCompagno.class},
                {AuctionAction_Dubbioso.class},
                {AuctionAction_Ambiguo.class}
        });
    }

    @Before
    public void setUp() throws Exception {
        inputHand = new MockHand(true);
        inputPlayer = new MockPlayer(false);
        Constructor<?> constructor = implClass.getConstructor();
        iAuctionActionTestObject = (IAuctionAction) constructor.newInstance();
    }

    @Test
    public void testChooseNextStance() throws Exception {
        Status beforeStatus = inputPlayer.getAuctionInfo().getStatus();
        Score beforeScore = inputPlayer.getAuctionInfo().getScore();
        AuctionInfo nextStance = iAuctionActionTestObject.chooseNextStance(inputPlayer, CURRENT_SCORE);
        assertNotNull(nextStance);
        Status afterStatus = nextStance.getStatus();
        Score afterScore = nextStance.getScore();

        checkScoreAndStatus(beforeStatus, beforeScore, afterStatus, afterScore);
    }

    private void checkScoreAndStatus(Status beforeStatus, Score beforeScore, Status afterStatus, Score afterScore) {
        String statusMessage = "<" + beforeStatus + ", " + afterStatus + ">";
        String scoreMessage = "<" + beforeScore + ", " + afterScore + ">";
        boolean statusCheck;
        boolean scoreCheck;
        switch (beforeStatus) {
            case FOLDED:
                statusCheck = afterStatus.hasFolded();
                scoreCheck = afterScore.compareTo(beforeScore) == 0;
                break;
            default:
                statusCheck = afterStatus.actionWasDone();
                if (afterStatus.hasFolded()) {
                    scoreCheck = afterScore.compareTo(beforeScore) == 0;
                } else {
                    scoreCheck = afterScore.compareTo(beforeScore) > 0;
                }
                break;
        }
        assertTrue(statusMessage, statusCheck);
        assertTrue(scoreMessage, scoreCheck);
    }

    @Test
    public void testChooseNextScore() throws Exception {
        Score nextScore = iAuctionActionTestObject.chooseNextScore(inputHand, CURRENT_SCORE);
        assertTrue(nextScore.getScore() + " is not greater than " + CURRENT_SCORE, nextScore.getScore() >
                CURRENT_SCORE);

    }
}