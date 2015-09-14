package strategy.auction;

import game.player.Hand;
import game.player.MockHand;
import game.player.MockPlayerNoSideDeck;
import game.player.Player;
import game.player.auction.AuctionStance;
import game.player.auction.Score;
import game.player.auction.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import strategy.auction.msdb5.*;

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

    private Class implClass;
    private Hand inputHand;
    private Player inputPlayer;

    public IAuctionActionTest(Class implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection initParameters() {
        return Arrays.asList(new Object[][]{
                {MockAuctionAction.class},
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
        inputPlayer = new MockPlayerNoSideDeck();
        Constructor<?> constructor = implClass.getConstructor();
        iAuctionActionTestObject = (IAuctionAction) constructor.newInstance();
    }

    @Test
    public void testChooseNextStance() throws Exception {
        Status beforeStatus = inputPlayer.getAuctionStance().getStatus();
        Score beforeScore = inputPlayer.getAuctionStance().getScore();
        AuctionStance nextStance = iAuctionActionTestObject.chooseNextStance(inputPlayer, CURRENT_SCORE);
        assertNotNull(nextStance);
        Status afterStatus = nextStance.getStatus();
        Score afterScore = nextStance.getScore();

        switch (beforeStatus) {
            case FOLDED:
                assertTrue(afterStatus == Status.FOLDED);
                break;
            default:
                assertTrue(afterStatus.actionWasDone());
                assertTrue(afterScore.compareTo(beforeScore) > 0);
                break;
        }
    }

    @Test
    public void testChooseNextScore() throws Exception {
        Score nextScore = iAuctionActionTestObject.chooseNextScore(inputHand, CURRENT_SCORE);
        assertTrue(nextScore.getScore() + " is not greater than " + CURRENT_SCORE, nextScore.getScore() >
                CURRENT_SCORE);

    }
}