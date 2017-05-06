package msdb5.gameplay.player;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.CardSuit;

import java.util.Random;

/**
 * Created by nikiforos on 06/05/17.
 */
public class MockCard extends Card {
    public MockCard() {
        super(CardNumber.values()[new Random().nextInt(CardNumber.values().length)],
                CardSuit.values()[new Random().nextInt(CardSuit.values().length)]);
    }
}
