package msdb5.game.card.set;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikiforos on 29/08/15.
 */
abstract class CardSetFactory {

    private final int cardSetSize;

    public CardSetFactory(int cardSetSize) {
        if (cardSetSize < 0) {
            throw new IllegalArgumentException("Card size cannot be less than 0");
        }
        this.cardSetSize = cardSetSize;
    }

    public abstract CardSet create();

    Collection<Card> createCardSet() {
        List<Card> localCardSet = createSortedCardSet();
        shuffleCardSet(localCardSet);
        return localCardSet.subList(0, this.cardSetSize);
    }

    private List<Card> createSortedCardSet() {
        List<Card> localCardSet = new LinkedList<>();
        for (CardNumber number : CardNumber.values()) {
            for (CardSuit suit : CardSuit.values()) {
                localCardSet.add(new Card(number, suit));
            }
        }
        return localCardSet;
    }

    private void shuffleCardSet(List<Card> cardList) {
        Collections.shuffle(cardList);
    }

}
