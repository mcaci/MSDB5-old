package game.factory;

import game.elements.Card;
import game.player.Hand;

import java.util.List;

/**
 * Created by nikiforos on 08/09/15.
 */
public class HandFactory extends CardSetFactory {

    public HandFactory(boolean isSizeDeckPresent) {
        super(isSizeDeckPresent ? Hand.WITH_SIDE_DECK_HAND_SIZE : Hand.WITHOUT_SIDE_DECK_HAND_SIZE);
    }

    public Hand createHand() {
        Hand createdHand = new Hand();
        List<Card> localHand = createCardSet();
        shuffleCardSet(localHand);
        createdHand.addCardSet(localHand, setSize);
        return createdHand;
    }
}