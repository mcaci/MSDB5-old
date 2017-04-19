package msdb5.game.card.set;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by nikiforos on 29/08/15.
 */
abstract class CardSetFactory {

    private final int cardSetSize;

    private Supplier<Collection<Card>> containerSupplier;

    public CardSetFactory(Supplier<Collection<Card>> containerSupplier, int cardSetSize) {
        if (cardSetSize < 0) {
            throw new IllegalArgumentException("Card size cannot be less than 0");
        }
        this.cardSetSize = cardSetSize;
        this.containerSupplier = containerSupplier;
    }

    public abstract CardSet create();

    Collection<Card> createCardSet() {
        return new Random().ints(0, cardSetSize).distinct().limit(cardSetSize).
                mapToObj(mapIdToCard()).collect(Collectors.toCollection(this.containerSupplier));
    }

    private IntFunction<Card> mapIdToCard() {
        return id -> {
            CardNumber[] numbers = CardNumber.values();
            CardSuit[] suits = CardSuit.values();
            final CardNumber number = numbers[id % numbers.length];
            final CardSuit suit = suits[id / numbers.length];
            return new Card(number, suit);
        };
    }
}
