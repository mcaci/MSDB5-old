package msdb5.game.card.set;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;

import java.util.Collection;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static msdb5.game.card.set.CardSet.MAX_CARDSET_SIZE;

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

    public abstract CardSet<? extends Collection<Card>> create();

    <T extends Collection<Card>> T createCardSet(Supplier<T> containerSupplier) {
        boolean isRequestedCardSetEmpty = cardSetSize == 0;
        CardCollectionFactory<T> cardCollectionFactory = isRequestedCardSetEmpty ?
                new EmptyCollectionFactory() : new NonEmptyCollectionFactory(this.cardSetSize);
        return cardCollectionFactory.getCardCollectionFactoryFunction().apply(containerSupplier);
    }

    private interface CardCollectionFactory<T extends Collection<Card>> {
        Function<Supplier<T>, T> getCardCollectionFactoryFunction();
    }

    private class EmptyCollectionFactory<T extends Collection<Card>> implements CardCollectionFactory {
        @Override
        public Function<Supplier<T>, T> getCardCollectionFactoryFunction() {
            return Supplier::get;
        }
    }

    private class NonEmptyCollectionFactory<T extends Collection<Card>> implements CardCollectionFactory {

        private final int cardSetSize;

        private final IntFunction<Card> MAP_ID_TO_CARD = id -> {
            CardNumber[] numbers = CardNumber.values();
            CardSuit[] suits = CardSuit.values();
            final CardNumber number = numbers[id % numbers.length];
            final CardSuit suit = suits[id / numbers.length];
            return new Card(number, suit);
        };

        public NonEmptyCollectionFactory(int cardSetSize) {
            this.cardSetSize = cardSetSize;
        }

        @Override
        public Function<Supplier<T>, T> getCardCollectionFactoryFunction() {
            return containerSupplier -> new Random().ints(0, MAX_CARDSET_SIZE).distinct().limit(cardSetSize).
                    mapToObj(MAP_ID_TO_CARD).collect(Collectors.toCollection(containerSupplier));
        }
    }

}
