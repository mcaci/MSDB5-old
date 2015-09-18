package game.factory;

import game.elements.cardset.Hand;

/**
 * Created by nikiforos on 08/09/15.
 */
public class HandFactory extends CardSetFactory {

    public HandFactory(boolean isSizeDeckPresent) {
        super(isSizeDeckPresent ? Hand.WITH_SIDE_DECK_HAND_SIZE : Hand.WITHOUT_SIDE_DECK_HAND_SIZE);
    }

    public Hand createHand() {
        return new Hand(createShuffledCardSet());
    }
}