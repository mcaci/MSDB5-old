package msdb5.game.card.set;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactory {

    public static final int DECK_SIZE = Deck.DEFAULT_DECK_SIZE;

    public Deck create() {
        final IntFunction<Card> idToCardFunction = id -> {
            CardNumber[] numbers = CardNumber.values();
            CardSuit[] suits = CardSuit.values();
            final CardNumber number = numbers[id % numbers.length];
            final CardSuit suit = suits[id / numbers.length];
            return new Card(number, suit);
        };

        Queue<Card> deckContainer = new Random().ints(0, DECK_SIZE).distinct().limit(DECK_SIZE).
                mapToObj(idToCardFunction).collect(Collectors.toCollection(ArrayDeque<Card>::new));

        return new Deck(deckContainer);
    }
}
