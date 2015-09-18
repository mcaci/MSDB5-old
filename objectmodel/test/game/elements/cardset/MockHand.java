package game.elements.cardset;

import game.factory.HandFactory;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockHand extends Hand {

    public MockHand(boolean isSizeDeckPresent) {
        HandFactory handFactory = new HandFactory(isSizeDeckPresent);
        Hand createdHand = handFactory.createHand();
        this.getCardSet().addAll(createdHand.getCardSet());
    }

}