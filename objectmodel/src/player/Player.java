package player;

import hand.Hand;

/**
 * Created by nikiforos on 30/08/15.
 */
public class Player {
    private final Hand hand = new Hand();

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                '}';
    }
}
