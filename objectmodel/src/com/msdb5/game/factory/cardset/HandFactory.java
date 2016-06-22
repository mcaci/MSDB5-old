package com.msdb5.game.factory.cardset;

import com.msdb5.game.cardset.Hand;

/**
 * Created by nikiforos on 08/09/15.
 */
public class HandFactory extends CardSetFactory {

    /**
     * Creates factory for side deck with size info
     */
    public HandFactory(int handSize) {
        super(handSize);
    }

    /**
     * Creates factory for side deck aware hand
     */
    public HandFactory(boolean isSizeDeckPresent) {
        super(isSizeDeckPresent ? Hand.WITH_SIDE_DECK_HAND_SIZE : Hand.WITHOUT_SIDE_DECK_HAND_SIZE);
    }

    public Hand createHand() {
        return new Hand(createCardSet());
    }
}