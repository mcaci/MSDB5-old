package msdb5.gameplay.pregame;

import msdb5.game.player.Player;
import msdb5.game.player.info.AuctionStatus;
import msdb5.game.table.GameTable;
import msdb5.game.table.PreparedGameTableFactory;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
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
    private Player winner;

    @Before
    public void setUp() throws Exception {
        winner = new AuctionRoulette().executeOn(PLAYERS);
    }

    @After
    public void tearDown() throws Exception {
        GameTable inputOutputGameTable = new PreparedGameTableFactory(false).create(PLAYERS);
        System.out.println("auctionRouletteTest: gameTable after the evaluation");
        System.out.println(inputOutputGameTable);
    }

    @Test
    public void testIsAuctionOver() throws Exception {
        assertNotNull(winner);
    }

    @Test
    public void testWinnerIsThePlayerReturnedByTheAuctionMethod() throws Exception {
        assertEquals(getWinnerPlayer(), winner);
    }

    @Test
    public void testWinnerHasMaxScore() throws Exception {
        assertEquals(Stream.of(PLAYERS).mapToInt(Player::tellAuctionScore).max().getAsInt(), getWinnerPlayer().tellAuctionScore());
    }

    @Test
    public void testThereIsOneWinnerOnly() throws Exception {
        assertEquals(getFilteredPlayersCount(WINNER_PREDICATE), WINNERS_COUNT);
    }

    @Test
    public void testOnFoldedPlayers() throws Exception {
        assertEquals(getFilteredPlayersCount(FOLDED_PREDICATE), FOLDED_PLAYERS_COUNT);
    }

    @Test
    public void testPlayersScoreAfterAuctionGreaterOrEqualsThanMin() throws Exception {
        assertTrue(verifyScoreConditionOver(auctionScore -> Integer.compare(auctionScore, AtomicAuctionTable.AUCTION_BASE) >= 0));
    }

    @Test
    public void testPlayersScoreAfterAuctionLessOrEqualsThanMax() throws Exception {
        assertTrue(verifyScoreConditionOver(auctionScore -> Integer.compare(auctionScore, AtomicAuctionTable.AUCTION_MAX) <= 0));
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