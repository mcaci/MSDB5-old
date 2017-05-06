package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by nikiforos on 29/08/15.
 */
public class CollectedCardSetFactory extends CardSetFactory {

    public CollectedCardSetFactory() {
        super(0);
    }

    @Override
    public CardSet<? extends Collection<Card>> create() {
        return new CardSet(this.createCardSet(HashSet<Card>::new)) {
        };
    }

}
