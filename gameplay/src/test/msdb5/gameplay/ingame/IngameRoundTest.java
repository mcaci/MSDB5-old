package msdb5.gameplay.ingame;

import msdb5.game.card.Card;
import msdb5.game.card.set.CardSet;
import msdb5.game.player.Player;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by mcaci on 5/24/17.
 */
public abstract class IngameRoundTest {

    private final Predicate<CardSet<? extends Collection<Card>>> sizeOfCardSetChangePredicate;

    IngameRoundTest(int playersHandSize, int sizeDelta){
        this.sizeOfCardSetChangePredicate = collectedCards -> collectedCards.size() == playersHandSize + sizeDelta;
    }

    public abstract boolean verify(Player[] players, Function<Player, CardSet<? extends Collection<Card>>> playersCards);

    Stream<CardSet<? extends Collection<Card>>> getCardSetStream(Player[] players, Function<Player, CardSet<? extends Collection<Card>>> playerCardSetFunction) {
        return Stream.of(players).map(playerCardSetFunction);
    }

    Predicate<CardSet<? extends Collection<Card>>> getSizeOfCardSetChangePredicate() {
        return sizeOfCardSetChangePredicate;
    }
}
