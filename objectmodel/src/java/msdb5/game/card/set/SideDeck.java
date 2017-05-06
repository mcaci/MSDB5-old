package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.ArrayList;

/**
 * Created by nikiforos on 06/05/17.
 */
public class SideDeck extends CardSet<ArrayList<Card>> {

    SideDeck(ArrayList<Card> cardSet) {
        super(cardSet);
    }

    @Override
    public String toString() {
        return "SideDeck{" +
               super.toString() +
               '}';
    }
}
