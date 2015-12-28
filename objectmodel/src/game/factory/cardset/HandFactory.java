package game.factory.cardset;

import game.elements.cardset.DeckAwareHand;

/**
 * Created by nikiforos on 08/09/15.
 */
public class HandFactory extends CardSetFactory {

    public HandFactory(boolean isSizeDeckPresent) {
        super(isSizeDeckPresent ? DeckAwareHand.WITH_SIDE_DECK_HAND_SIZE : DeckAwareHand.WITHOUT_SIDE_DECK_HAND_SIZE);
    }

    public DeckAwareHand createHand() {
        return new DeckAwareHand(createShuffledCardSet());
    }
}