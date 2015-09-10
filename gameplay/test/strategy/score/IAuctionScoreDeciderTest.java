package strategy.score;

import game.player.Hand;
import game.player.MockHand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by nikiforos on 10/09/15.
 */
@RunWith(Parameterized.class)
public class IAuctionScoreDeciderTest {

    private IAuctionScoreDecider iAuctionScoreDeciderTestObject;

    private Class implClass;
    private Hand inputHand;

    public IAuctionScoreDeciderTest(Class implClass) {
        this.implClass = implClass;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {AuctionScoreDecider_Rialzatore.class},
                {AuctionScoreDecider_CampioneDecaduto.class},
                {AuctionScoreDecider_BuonCompagno.class},
                {AuctionScoreDecider_Dubbioso.class},
                {AuctionScoreDecider_Ambiguo.class}
        });
    }

    @Before
    public void setUp() throws Exception {
        inputHand = new MockHand(true);
        Constructor<?> constructor = implClass.getConstructor();
        iAuctionScoreDeciderTestObject = (IAuctionScoreDecider) constructor.newInstance();
    }

    @Test
    public void testChooseNextScore() throws Exception {

    }
}