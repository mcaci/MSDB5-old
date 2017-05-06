package msdb5.gameplay.endgame;

import msdb5.game.card.set.DeckFactory;
import msdb5.game.player.Player;
import msdb5.game.player.info.InGameStatus;
import msdb5.game.table.GameTable;
import msdb5.game.table.PreparedGameTableFactory;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 18/02/16.
 */
public class ScoreCountRouletteTest {

    public static final Predicate<Player> IN_GAME_STATUS_PREDICATE = player -> !(player.getInGameStatus() == InGameStatus.COMPETITOR);
    private GameTable inputOutputGameTable;

    @Before
    public void setUp() throws Exception {
        Player[] testPlayers = {new TestPlayerForGamePlayer(1), new TestPlayerForGamePlayer(2), new TestPlayerForGamePlayer(3), new TestPlayerForGamePlayer(4), new TestPlayerForGamePlayer(5)};
        assignMockCardPilesToPlayers(testPlayers);
        inputOutputGameTable = new PreparedGameTableFactory(true).create(testPlayers);
        ScoreCountRoulette scoreCountRouletteTest = new ScoreCountRoulette();
        scoreCountRouletteTest.executeOn(inputOutputGameTable);
    }

    private void assignMockCardPilesToPlayers(Player[] testPlayers) {
        testPlayers[0].getCollectedCards().getCardSet().addAll(new DeckFactory().create().getCardSet());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("scoreCountRouletteTest: gameTable after the score count");
        System.out.println(inputOutputGameTable);
    }

    @Test
    public void testOverallScore() throws Exception {
        Player[] players = inputOutputGameTable.getPlayers();
        byte overallScore = 0;
        for (Player player : players) {
            overallScore += player.getGameScore();
        }
        assertEquals(120, overallScore);
    }

    @Test
    public void testAuctionWinnerHasWon() throws Exception {
        Player[] players = setUpPlayers(0, 2);
        int[] sums = partitionPlayers(players);
        int callersTeamScore = sums[0];
        int competitorsTeamScore = sums[1];
        assertTrue("callersTeamScore is: " + callersTeamScore + " and competitorsTeamScore is: " + competitorsTeamScore,
                callersTeamScore >= competitorsTeamScore);
    }

    @Test
    public void testAuctionWinnerHasLost() throws Exception {
        Player[] players = setUpPlayers(1, 2);
        int[] sums = partitionPlayers(players);
        int callersTeamScore = sums[0];
        int competitorsTeamScore = sums[1];
        assertTrue("callersTeamScore is: " + callersTeamScore + " and competitorsTeamScore is: " + competitorsTeamScore,
                callersTeamScore < competitorsTeamScore);
    }

    private Player[] setUpPlayers(int auctionWinnerIndex, int companionIndex) {
        Player[] players = inputOutputGameTable.getPlayers();
        assignInGameRolesToPlayers(players, auctionWinnerIndex, companionIndex);
        return players;
    }

    private int[] partitionPlayers(Player[] players) {
        int[] sums = new int[2];
        Arrays.asList(players).forEach(player -> {
            if (IN_GAME_STATUS_PREDICATE.test(player)) {
                sums[0] += player.getGameScore();
            } else {
                sums[1] += player.getGameScore();
            }
        });
        return sums;
    }

    private void assignInGameRolesToPlayers(Player[] testPlayers, int auctionWinnerIndex, int companionIndex) {
        Arrays.stream(testPlayers).forEach(player -> player.setInGameStatus(InGameStatus.COMPETITOR));
        testPlayers[auctionWinnerIndex].setInGameStatus(InGameStatus.AUCTION_WINNER);
        testPlayers[companionIndex].setInGameStatus(InGameStatus.COMPANION);
    }
}