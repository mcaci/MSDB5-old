package game.elements.cardset;

import game.factory.cardset.HandFactory;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockDeckAwareHand extends DeckAwareHand {

    public MockDeckAwareHand(boolean isSizeDeckPresent) {
        HandFactory handFactory = new HandFactory(isSizeDeckPresent);
        DeckAwareHand createdDeckAwareHand = handFactory.createHand();
        this.getCardSet().addAll(createdDeckAwareHand.getCardSet());
    }

}