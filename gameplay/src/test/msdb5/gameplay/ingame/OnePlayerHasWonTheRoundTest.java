package msdb5.gameplay.ingame;

import msdb5.game.card.Card;
import msdb5.game.card.set.CardSet;
import msdb5.game.player.Player;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by mcaci on 5/24/17.
 */
public class OnePlayerHasWonTheRoundTest extends IngameRoundTest {

    OnePlayerHasWonTheRoundTest(Player player) {
        super(player.getCollectedCards().size(), 5);
    }

    @Override
    public boolean verify(Player[] players, Function<Player, CardSet<? extends Collection<Card>>> playersCards) {
        return getCardSetStream(players, playersCards).anyMatch(getSizeOfCardSetChangePredicate());
    }
}
