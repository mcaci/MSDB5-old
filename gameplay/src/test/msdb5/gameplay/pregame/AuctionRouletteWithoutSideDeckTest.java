package msdb5.gameplay.pregame;

import msdb5.game.player.Player;
import msdb5.game.player.info.AuctionStatus;
import msdb5.game.table.GameTable;
import msdb5.game.table.PreparedGameTableFactory;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 01/09/15.
 */
public class AuctionRouletteWithoutSideDeckTest {

    public static final Predicate<AuctionStatus> WINNER_PREDICATE = (status) -> status == AuctionStatus.AUCTION_WINNER;
    public static final Predicate<AuctionStatus> FOLDED_PREDICATE = (status) -> status == AuctionStatus.FOLDED;
    private static final int FOLDED_PLAYERS_COUNT = 4;
    private static final int WINNERS_COUNT = 1;
    private static final Player[] PLAYERS = {new TestPlayerForGamePlayer(1), new TestPlayerForGamePlayer(2),
            new TestPlayerForGamePlayer(3), new TestPlayerForGamePlayer(4), new TestPlayerForGamePlayer(5)};
    private static final GameTable GAME_TABLE = new PreparedGameTableFactory(false).create(PLAYERS);
    private Player winner;

    @Before
    public void setUp() throws Exception {
        winner = new AuctionRoulette().executeOn(PLAYERS);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("auctionRouletteTest: gameTable after the evaluation");
        System.out.println(GAME_TABLE);
    }

    @Test
    public void testIsAuctionOver() throws Exception {
        assertNotNull(winner);
    }

    @Test
    public void testWinnerIsThePlayerReturnedByTheAuctionMethod() throws Exception {
        assertEquals(winner, getWinnerPlayer());
    }

    @Test
    public void testWinnerHasMaxScore() throws Exception {
        assertEquals(getWinnerPlayer().tellAuctionScore(), Stream.of(PLAYERS).mapToInt(Player::tellAuctionScore).max().getAsInt());
    }

    @Test
    public void testThereIsOneWinnerOnly() throws Exception {
        assertEquals(WINNERS_COUNT, getFilteredPlayersCount(WINNER_PREDICATE));
    }

    @Test
    public void testOnFoldedPlayers() throws Exception {
        assertEquals(FOLDED_PLAYERS_COUNT, getFilteredPlayersCount(FOLDED_PREDICATE));
    }

    @Test
    public void testPlayersScoreAfterAuctionGreaterOrEqualsThanMin() throws Exception {
        assertTrue(verifyScoreConditionOver(auctionScore -> Integer.compare(auctionScore, AuctionRoulette.AUCTION_BASE) >= 0));
    }

    @Test
    public void testPlayersScoreAfterAuctionLessOrEqualsThanMax() throws Exception {
        assertTrue(verifyScoreConditionOver(auctionScore -> Integer.compare(auctionScore, AuctionRoulette.AUCTION_MAX) <= 0));
    }

    private boolean verifyScoreConditionOver(IntPredicate auctionScorePredicate) {
        return Stream.of(PLAYERS).mapToInt(Player::tellAuctionScore).allMatch(auctionScorePredicate);
    }

    private Player getWinnerPlayer() {
        return getFilteredPlayerStream(WINNER_PREDICATE).findFirst().get();
    }

    private long getFilteredPlayersCount(Predicate<AuctionStatus> statusPredicate) {
        return getFilteredPlayerStream(statusPredicate).count();
    }

    private Stream<Player> getFilteredPlayerStream(Predicate<AuctionStatus> statusPredicate) {
        return Stream.of(PLAYERS).filter(getPlayerPredicate(statusPredicate));
    }

    private Predicate<Player> getPlayerPredicate(Predicate<AuctionStatus> statusPredicate) {
        return player -> player.getAuctionStatusFor(statusPredicate);
    }
}