package msdb5.gameplay;

/**
 * Created by nikiforos on 08/02/16.
 */
public class FullGamePlayTest {

//    private GameTable inputOutputGameTable;
//    private String stepName;
//
//    @Before
//    public void setUp() throws Exception {
//        Player[] testPlayers = {new Ambiguo(), new Buonagno(), new CampioneDecaduto(), new Dubbioso(), new Rialzatore()};
//        inputOutputGameTable = new GameTableFactory(true).create(testPlayers);
//        stepName = "";
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        System.out.println("FullGamePlayTest: step " + stepName);
//        System.out.println(inputOutputGameTable);
//    }
//
//    @Test
//    public void step1() throws Exception {
//        stepName = "Preparation (auction, choosing suit, managing side deck, choosing number/card)";
//        AuctionRoulette auctionRouletteTest = new AuctionRoulette();
//        auctionRouletteTest.executeOn(inputOutputGameTable);
//
//        Player auctionWinner = inputOutputGameTable.getGameTableInfo().getAuctionWinner();
//        assertNotNull(auctionWinner);
//        Card anionCard = inputOutputGameTable.getGameTableInfo().getPairedPlayerCard();
//        assertNotNull(anionCard);
//    }
//
//    @Test
//    public void step2() throws Exception {
//        step1();
//        stepName = "Hostilities (play all rounds until all card are played)";
//        HostilitiesRoulette hostilitiesRouletteTest = new HostilitiesRoulette();
//        hostilitiesRouletteTest.executeOn(inputOutputGameTable);
//        for (Player player : inputOutputGameTable.getPlayers()) {
//            assertEquals(0, player.getHand().size());
//        }
//    }
//
//    @Test
//    public void step3() throws Exception {
//        step2();
//        stepName = "Score count (count all points and determine winners)";
//        ScoreCountRoulette scoreCountRouletteTest = new ScoreCountRoulette();
//        scoreCountRouletteTest.executeOn(inputOutputGameTable);
//        int sumOfScores = 0;
//        for (Player player : inputOutputGameTable.getPlayers()) {
//            sumOfScores += player.tellScore();
//        }
//        assertEquals(120, sumOfScores);
//    }
}
