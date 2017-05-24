package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.List;

/**
 * Created by nikiforos on 06/05/17.
 */
public class SideDeck extends CardSet<List<Card>> {

    SideDeck(List<Card> cardSet) {
        super(cardSet);
    }

    @Override
    public String toString() {
        return "SideDeck{" +
               super.toString() +
               '}';
    }
}
