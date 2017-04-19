package msdb5.game.card.set;

import msdb5.game.card.Card;

import java.util.Collection;

/**
 * Created by nikiforos on 16/09/15.
 */
public abstract class CardSet<T extends Collection<Card>> {

    private final T cardSet;

    CardSet(T cardSet) {
        this.cardSet = cardSet;
    }

    public T getCardSet() {
        return this.cardSet;
    }

    public boolean contains(Card toDraw) {
        return this.cardSet.contains(toDraw);
    }

    public boolean isEmpty() {
        return this.cardSet.isEmpty();
    }

    public int size() {
        return this.cardSet.size();
    }

    @Override
    public String toString() {
        return "CardSet{" +
                "cardSet=" + this.cardSet +
                ", size=" + this.cardSet.size() +
                '}';
    }
}
