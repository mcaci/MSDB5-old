package game.elements.cardset;

import game.elements.base.Card;

import java.util.Collection;

/**
 * Created by nikiforos on 16/09/15.
 */
public class CardSet<T extends Collection<Card>> {

    private final T cardSet;

    public CardSet(T cardSet) {
        this.cardSet = cardSet;
    }

    public T getCardSet() {
        return cardSet;
    }

    public boolean isEmpty() {
        return this.cardSet.isEmpty();
    }

    @Override
    public String toString() {
        return "CardSet{" +
                "cardSet=" + this.cardSet +
                ", size=" + this.cardSet.size() +
                '}';
    }
}
