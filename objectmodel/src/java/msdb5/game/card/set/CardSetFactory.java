package msdb5.game.card.set;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        IntStream cardsIds = IntStream.range(0, cardSetSize);
        List<Card> cards = cardsIds.mapToObj(getCardFunction()).collect(Collectors.toCollection(LinkedList<Card>::new));
        shuffleCardSet(cards);
        return cards;
    }

    private IntFunction<Card> getCardFunction() {
        return id -> {
            CardNumber[] numbers = CardNumber.values();
            CardSuit[] suits = CardSuit.values();
            final CardNumber number = numbers[id % numbers.length];
            final CardSuit suit = suits[id / numbers.length];
            return new Card(number, suit);
        };
    }

    private void shuffleCardSet(List<Card> cardList) {
        Collections.shuffle(cardList);
    }

}
