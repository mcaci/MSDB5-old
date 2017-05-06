package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.HashSet;

/**
 * Created by nikiforos on 08/09/15.
 */
public class HandFactory extends CardSetFactory {

    /**
     * Creates factory for side deck aware hand
     */
    public HandFactory(boolean isSizeDeckPresent) {
        super(isSizeDeckPresent ? Hand.WITH_SIDE_DECK_HAND_SIZE : Hand.WITHOUT_SIDE_DECK_HAND_SIZE);
    }

    public HandFactory() {
        super(0);
    }

    @Override
    public Hand create() {
        return new Hand(this.createCardSet(HashSet<Card>::new));
    }
}