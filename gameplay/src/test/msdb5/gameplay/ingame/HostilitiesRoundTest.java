package msdb5.gameplay.ingame;

import msdb5.game.card.Card;
import msdb5.game.card.set.CardSet;
import msdb5.game.card.set.Hand;
import msdb5.game.player.Player;
import msdb5.game.table.GameTable;
import msdb5.game.table.GameTableFactory;
import msdb5.gameplay.GameRoulette;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 22/05/17.
 */
public class HostilitiesRoundTest {


    private GameTable gameTable;

    @Before
    public void setUp() throws Exception {
        Player[] players = {new TestPlayerForGamePlayer(1), new TestPlayerForGamePlayer(2),
                new TestPlayerForGamePlayer(3), new TestPlayerForGamePlayer(4), new TestPlayerForGamePlayer(5)};
        gameTable = new GameTableFactory(true).create(players);
    }

    @Test
    public void testAllPlayersAreOneCardShort() throws Exception {
        final Player[] players = gameTable.getPlayers();
        final int size = players[0].getHand().size();
        executeRound();
        boolean everyoneHasPlayedACard = isEveryoneHasPlayedACard(players, size);
        assertTrue(everyoneHasPlayedACard);
    }

    @Test
    public void testOnePlayerHasWonFiveCards() throws Exception {
        final Player[] players = gameTable.getPlayers();
        final int size = players[0].getCollectedCards().size();
        executeRound();
        boolean onePlayerHasWonTheRound = isOnePlayerHasWonTheRound(players, size);
        assertTrue(onePlayerHasWonTheRound);
    }

    private void executeRound() {
        GameRoulette round = this::performHostilitiesRound;
        round.executeOn(gameTable);
    }

    private boolean isEveryoneHasPlayedACard(Player[] players, int playersHandSize) {
        final Predicate<Hand> handIsOneCardShorter = hand -> hand.size() == playersHandSize - 1;
        return Arrays.stream(players).map(player -> player.getHand()).allMatch(handIsOneCardShorter);
    }

    private boolean isOnePlayerHasWonTheRound(Player[] players, int playersHandSize) {
        final Predicate<CardSet<? extends Collection<Card>>> cardCollectedAreFiveMore = collectedCards -> collectedCards.size() == playersHandSize + 5;
        return Arrays.stream(players).map(player -> player.getCollectedCards()).anyMatch(cardCollectedAreFiveMore);
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
