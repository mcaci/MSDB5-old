package game.factory;

import game.elements.Card;
import game.elements.CardNumber;
import game.elements.CardSuit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikiforos on 29/08/15.
 */
abstract class CardSetFactory {

    final int setSize;

    public CardSetFactory(int setSize) {
        this.setSize = setSize;
    }

    List<Card> createCardSet() {
        List<Card> localDeck = new LinkedList<>();
        final CardSuit[] suits = CardSuit.values();
        final CardNumber[] numbers = CardNumber.values();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                localDeck.add(new Card(numbers[i], suits[j]));
            }
        }
        return localDeck;
    }

    void shuffleCardSet(List<Card> cardList) {
        Collections.shuffle(cardList);
    }

}
