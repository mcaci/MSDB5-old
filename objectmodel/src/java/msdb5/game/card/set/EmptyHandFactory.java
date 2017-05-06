package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.HashSet;

/**
 * Created by nikiforos on 08/09/15.
 */
public class EmptyHandFactory extends EmptyCardSetFactory {
    @Override
    public Hand create() {
        return new Hand(this.createCardSet(HashSet<Card>::new));
    }
}