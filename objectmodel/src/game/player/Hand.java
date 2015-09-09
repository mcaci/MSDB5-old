package game.player;

import game.elements.Card;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikiforos on 29/08/15.
 */
public class Hand {

    public static final int WITH_SIDE_DECK_HAND_SIZE = 7;
    public static final int WITHOUT_SIDE_DECK_HAND_SIZE = 8;

    private final List<Card> hand;

    public Hand() {
        this.hand = new LinkedList<>();
    }

    public void add(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "hand=" + hand +
                ", size=" + this.hand.size() +
                '}';
    }

    public void addCardSet(List<Card> cardList, int handSize) {
        this.hand.addAll(cardList.subList(0, handSize));
    }

    public void addCardSet(List<Card> hand) {
        this.addCardSet(hand, hand.size());
    }

    public boolean isEmpty() {
        return this.hand.isEmpty();
    }

}
