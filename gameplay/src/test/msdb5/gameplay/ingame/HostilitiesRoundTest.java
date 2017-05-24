package msdb5.gameplay.ingame;

import msdb5.game.card.Card;
import msdb5.game.player.Player;
import msdb5.game.table.GameTable;
import msdb5.game.table.GameTableFactory;
import msdb5.gameplay.GameRoulette;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 22/05/17.
 */
public class HostilitiesRoundTest {

    private GameTable gameTable;
    private HostilitiesRoundVerificationTest roundTest;

    @Before
    public void setUp() throws Exception {
        final Player[] players = {new TestPlayerForGamePlayer(1), new TestPlayerForGamePlayer(2),
                new TestPlayerForGamePlayer(3), new TestPlayerForGamePlayer(4), new TestPlayerForGamePlayer(5)};
        gameTable = new GameTableFactory(true).create(players);
    }

    @Test
    public void testAllPlayersAreOneCardShort() throws Exception {
        roundTest = new EveryoneHasPlayedACardTest(this.gameTable.getPlayers()[0]);
        executeRound(gameTable);
        assertTrue(roundTest.verify(this.gameTable.getPlayers(), player -> player.getHand()));
    }

    @Test
    public void testOnePlayerHasWonFiveCards() throws Exception {
        roundTest = new OnePlayerHasWonTheRoundTest(this.gameTable.getPlayers()[0]);
        executeRound(gameTable);
        assertTrue(roundTest.verify(this.gameTable.getPlayers(), player -> player.getCollectedCards()));
    }

    private void executeRound(GameTable gameTable) {
        GameRoulette round = this::performHostilitiesRound;
        round.executeOn(gameTable);
    }

    private void performHostilitiesRound(GameTable gameTable) {
        Player playerOne = gameTable.getPlayers()[0];
        for (Player player : gameTable.getPlayers()) {
            Card chosen = player.getHand().getCardSet().iterator().next();
            player.getHand().getCardSet().remove(chosen);
            playerOne.getCollectedCards().add(chosen);
        }
    }
}
