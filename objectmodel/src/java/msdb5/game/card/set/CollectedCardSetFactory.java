package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by nikiforos on 29/08/15.
 */
public class CollectedCardSetFactory extends EmptyCardSetFactory {
    @Override
    public CardSet<Collection<Card>> create() {
        return new CardSet<Collection<Card>>(this.createCardSet(HashSet<Card>::new)) {};
    }



}
