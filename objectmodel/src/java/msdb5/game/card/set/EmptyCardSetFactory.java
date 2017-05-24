package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by nikiforos on 08/09/15.
 */
abstract class EmptyCardSetFactory {

    public abstract CardSet<? extends Collection<Card>> create();

    <T extends Collection<Card>> T createCardSet(Supplier<T> containerSupplier) {
        return containerSupplier.get();
    }
}