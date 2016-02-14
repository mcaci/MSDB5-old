package gameplay;

import game.cardset.card.Card;
import game.factory.table.PreparedGameTableFactoryTest;
import game.player.Player;
import game.table.GameTable;
import gameplay.auction.AuctionRoulette;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 08/02/16.
 */
public class FullGamePlayTest {

    private AuctionRoulette auctionRouletteTest;
    private GameTable inputOutputGameTable;

    private String stepName;

    @Before
    public void setUp() throws Exception {
        auctionRouletteTest = new AuctionRoulette();
        inputOutputGameTable = new PreparedGameTableFactoryTest(true).getMockGameTable();

        stepName = "";
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("FullGamePlayTest: step " + stepName);
        System.out.println(inputOutputGameTable);
    }

    @Test
    public void step1() throws Exception {
        stepName = "Preparation (auction, choosing suit, managing side deck, choosing number/card)";
        auctionRouletteTest.execute(inputOutputGameTable);

        Player auctionWinner = inputOutputGameTable.getInfo().getAuctionWinner();
        assertNotNull(auctionWinner);
        System.out.println("Auction winner: " + auctionWinner);

        Card companionCard = auctionWinner.chooseCompanionCard();
        assertNotNull(companionCard);
        System.out.println("Companion card: " + companionCard);

//        Player companion = playerFinder.findCompanion();
    }

    @Test
    public void step2() throws Exception {
        step1();

        stepName = "Hostilities (play all rounds until all card are played)";
    }

    @Test
    public void step3() throws Exception {
        step2();

        stepName = "Score count (count all points and determine winners)";
    }
}
