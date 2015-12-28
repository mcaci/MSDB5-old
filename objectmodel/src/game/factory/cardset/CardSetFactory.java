package game.factory.cardset;

import game.elements.base.Card;
import game.elements.base.CardNumber;
import game.elements.base.CardSuit;

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
        this.cardSetSize = cardSetSize;
    }

    Collection<Card> createCardSet() {
        Collection<Card> localCardSet = Collections.EMPTY_LIST;
        if (cardSetSize > 0) {
            localCardSet = createShuffledCardSet();
        }
        return localCardSet;
    }

    private Collection<Card> createShuffledCardSet() {
        List<Card> localCardSet = createSortedCardSet();
        shuffleCardSet(localCardSet);
        return localCardSet.subList(0, this.cardSetSize);
    }

    private List<Card> createSortedCardSet() {
        List<Card> localCardSet = new LinkedList<>();
        final CardSuit[] suits = CardSuit.values();
        final CardNumber[] numbers = CardNumber.values();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                localCardSet.add(new Card(numbers[i], suits[j]));
            }
        }
        return localCardSet;
    }

    private void shuffleCardSet(List<Card> cardList) {
        Collections.shuffle(cardList);
    }

}
