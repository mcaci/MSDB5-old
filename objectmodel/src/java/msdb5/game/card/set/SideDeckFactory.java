package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.ArrayList;

/**
 * Created by nikiforos on 06/05/17.
 */
public class SideDeckFactory extends CardSetFactory {

    public SideDeckFactory() {
        super(0);
    }

    @Override
    public SideDeck create() {
        return new SideDeck(this.createCardSet(ArrayList<Card>::new));
    }
}
