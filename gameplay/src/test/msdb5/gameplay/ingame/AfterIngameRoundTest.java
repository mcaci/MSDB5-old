package msdb5.gameplay.ingame;

import msdb5.game.card.Card;
import msdb5.game.card.set.CardSet;
import msdb5.game.player.Player;
import msdb5.game.table.GameTable;
import msdb5.game.table.GameTableFactory;
import msdb5.gameplay.GameRoulette;
import msdb5.gameplay.player.TestPlayerForGamePlayer;
import org.junit.Before;

import java.util.Collection;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 22/05/17.
 */
public class AfterIngameRoundTest {

    GameTable gameTable;
    IngameRoundTest roundTest;

    @Before
    public void setUp() throws Exception {
        final Player[] players = {new TestPlayerForGamePlayer(1), new TestPlayerForGamePlayer(2),
                new TestPlayerForGamePlayer(3), new TestPlayerForGamePlayer(4), new TestPlayerForGamePlayer(5)};
        gameTable = new GameTableFactory(true).create(players);
    }

    private void testCardSetsAfterIngameRound(IngameRoundTest roundTest, Function<Player, CardSet<? extends Collection<Card>>> playerCardSetFunction) {
        Player[] players = this.gameTable.getPlayers();
        assertTrue(roundTest.verify(players, playerCardSetFunction));
    }

    private void executeRound(GameTable gameTable) {
        GameRoulette round = new SimpleIngameRoundRoulette();
        round.executeOn(gameTable);
    }

    void execute(Function<Player, CardSet<? extends Collection<Card>>> playerCardSetFunction){
        executeRound(gameTable);
        testCardSetsAfterIngameRound(roundTest, playerCardSetFunction);
    }

}
