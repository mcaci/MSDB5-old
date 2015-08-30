package table.factory;

import deck.Deck;
import deck.factory.RandomDeckFactory;
import player.Player;
import table.GameTable;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactory {

    public static final int NUMBER_OF_PLAYERS = 5;

    public GameTable getCreatedGameTable() {
        GameTable gameTable = new GameTable();

        RandomDeckFactory deckFactory = new RandomDeckFactory();
        Deck deck = deckFactory.getCreatedDeck();
        gameTable.setDeck(deck);

        Player[] players = new Player[NUMBER_OF_PLAYERS];
        gameTable.setPlayers(players);

        return gameTable;
    }
}
