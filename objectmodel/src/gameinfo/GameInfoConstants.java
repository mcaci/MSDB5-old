package gameinfo;

import game.elements.CardNumber;
import game.elements.CardSuit;

/**
 * Created by nikiforos on 31/08/15.
 */
public class GameInfoConstants {

    public static final int MAX_DECK_SIZE = CardNumber.values().length * CardSuit.values().length;
    public static final int NUMBER_OF_PLAYERS = 5;
    public static final int MIN_AUCTION_SCORE = 60;
    public static final int MAX_AUCTION_SCORE = 120;

}
