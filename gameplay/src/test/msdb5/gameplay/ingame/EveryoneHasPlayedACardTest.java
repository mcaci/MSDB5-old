package msdb5.gameplay.ingame;

import msdb5.game.card.Card;
import msdb5.game.card.set.CardSet;
import msdb5.game.player.Player;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by mcaci on 5/24/17.
 */
public class EveryoneHasPlayedACardTest extends HostilitiesRoundVerificationTest {

    EveryoneHasPlayedACardTest(Player player) {
        super(player.getHand().size(), -1);
    }

    @Override
    public boolean verify(Player[] players, Function<Player, CardSet<? extends Collection<Card>>> playersCards) {
        return getCardSetStream(players, playersCards).allMatch(getSizeOfCardSetChangePredicate());
    }
}
