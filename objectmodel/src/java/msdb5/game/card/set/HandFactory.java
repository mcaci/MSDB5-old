package msdb5.game.card.set;

import java.util.ArrayList;

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

    @Override
    public CardSet create() {
        return new Hand(new ArrayList<>(super.createCardSet()));
    }
}