package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikiforos on 08/09/15.
 */
public class HandFactory extends CardSetFactory {

    /**
     * Creates factory for side deck aware hand
     */
    public HandFactory(boolean isSizeDeckPresent) {
        super(HashSet<Card>::new, isSizeDeckPresent ? Hand.WITH_SIDE_DECK_HAND_SIZE : Hand.WITHOUT_SIDE_DECK_HAND_SIZE);
    }

    @Override
    public Hand create() {
        return new Hand((Set<Card>) this.createCardSet());
    }
}